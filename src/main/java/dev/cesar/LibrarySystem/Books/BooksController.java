package dev.cesar.LibrarySystem.Books;

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
    public BooksModel createBook(@RequestBody BooksModel book) {
        return booksService.createBook(book);
    }

    @GetMapping("/list")
    public List<BooksModel> listAllBooks() {
        return booksService.listAllBooks();
    }

    @GetMapping("/list/{id}")
    public BooksModel listBooksById(@PathVariable Long id) {
        return booksService.listBookById(id);
    }

    @PutMapping("/update/{id}")
    public BooksModel updateBookById(@PathVariable Long id, @RequestBody BooksModel book) {
        return booksService.updateBookById(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookById(@PathVariable Long id) {
        booksService.deleteBookById(id);
    }


}
