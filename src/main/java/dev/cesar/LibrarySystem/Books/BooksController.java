package dev.cesar.LibrarySystem.Books;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new book", description = "This route creates a new book and inserts it on the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "book successfully created"),
            @ApiResponse(responseCode = "400", description = "book creation error")
    })
    public ResponseEntity<String > createBook(
            @Parameter(description = "user sends the data of the book that will be created on the request path") @RequestBody BooksDTO book) {
        BooksDTO newBook = booksService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book successfully created: " + newBook.getTitle() + ". ID: " + newBook.getId());
    }

    @GetMapping("/list")
    @Operation(summary = "List all books", description = "This route lists all the books in the database")
    @ApiResponse(responseCode = "200", description = "books listed")
    public ResponseEntity<List<BooksDTO>> listAllBooks() {
        List<BooksDTO> books = booksService.listAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "List a book by ID", description = "This route lists a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book listed"),
            @ApiResponse(responseCode = "404", description = "book not found")
    })
    public ResponseEntity<?> listBooksById(
            @Parameter(description = "user sends the ID of the book that will be listed on the request path") @PathVariable Long id) {
        BooksDTO book = booksService.listBookById(id);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book of ID: " + id + " not found.");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a book data", description = "This route gets a book by its ID and updates its data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book updated"),
            @ApiResponse(responseCode = "404", description = "book not found. data not updated")
    })
    public ResponseEntity<String> updateBookById(
            @Parameter(description = "user sends the ID of the book that will be updated on the request path") @PathVariable Long id,
            @Parameter(description = "user sends the data of the book that will be updated on the request body") @RequestBody BooksDTO book) {
        BooksDTO updatedBook = booksService.updateBookById(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok("Book of ID: " + id + " updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book of ID: " + id + " not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a book", description = "This route gets a book by its ID and deletes it from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book deleted"),
            @ApiResponse(responseCode = "404", description = "book not found. book not deleted")
    })
    public ResponseEntity<String> deleteBookById(
            @Parameter(description = "user sends the ID of the book that will be deleted on the request path") @PathVariable Long id) {
        if (booksService.listBookById(id) != null) {
            booksService.deleteBookById(id);
            return ResponseEntity.ok("Book of ID: " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book of ID: " + id + " not found.");
        }
    }


}
