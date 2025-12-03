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
    public BooksDTO createBook(@RequestBody BooksDTO book) {
        return booksService.createBook(book);
    }

    @GetMapping("/list")
    public List<BooksDTO> listAllBooks() {
        return booksService.listAllBooks();
    }

    @GetMapping("/list/{id}")
    public BooksDTO listBooksById(@PathVariable Long id) {
        return booksService.listBookById(id);
    }

    @PutMapping("/update/{id}")
    public BooksDTO updateBookById(@PathVariable Long id, @RequestBody BooksDTO book) {
        return booksService.updateBookById(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookById(@PathVariable Long id) {
        booksService.deleteBookById(id);
    }


}
