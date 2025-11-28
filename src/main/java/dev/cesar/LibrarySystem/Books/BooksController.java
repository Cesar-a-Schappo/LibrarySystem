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
    public String createBook() {
        return "book created";
    }

    @GetMapping("/list")
    public List<BooksModel> listAllBooks() {
        return booksService.listAllBooks();
    }

    @GetMapping("/list/{id}")
    public String listBooksById() {
        return "book listed by its id";
    }

    @PutMapping("/change/{id}")
    public String changeBookById() {
        return "book changed by its id";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBookById() {
        return "book deleted by its id";
    }


}
