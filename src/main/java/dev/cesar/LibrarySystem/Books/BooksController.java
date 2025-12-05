package dev.cesar.LibrarySystem.Books;

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
    public ResponseEntity<String > createBook(@RequestBody BooksDTO book) {
        BooksDTO newBook = booksService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book successfully created: " + newBook.getTitle() + ". ID: " + newBook.getId());
    }

    @GetMapping("/list")
    public ResponseEntity<List<BooksDTO>> listAllBooks() {
        List<BooksDTO> books = booksService.listAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> listBooksById(@PathVariable Long id) {
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
    public ResponseEntity<String> updateBookById(@PathVariable Long id, @RequestBody BooksDTO book) {
        BooksDTO updatedBook = booksService.updateBookById(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok("Book of ID: " + id + " updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book of ID: " + id + " not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        if (booksService.listBookById(id) != null) {
            booksService.deleteBookById(id);
            return ResponseEntity.ok("Book of ID: " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book of ID: " + id + " not found.");
        }
    }


}
