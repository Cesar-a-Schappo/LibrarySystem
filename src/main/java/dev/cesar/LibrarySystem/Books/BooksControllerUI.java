package dev.cesar.LibrarySystem.Books;

import dev.cesar.LibrarySystem.Readers.ReadersDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("books/ui")
public class BooksControllerUI {

    private final BooksService booksService;

    public BooksControllerUI(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/list")
    public String listAllBooks(Model model) {
        List<BooksDTO> books = booksService.listAllBooks();
        model.addAttribute("books", books);
        return "BooksHTML/ListBooks";
    }

    @GetMapping("/list/{id}")
    public String listBooksById(@PathVariable Long id, Model model) {
        BooksDTO book = booksService.listBookById(id);
        model.addAttribute("book", book);
        if (book != null) {
            return "BooksHTML/BookDetails";
        } else {
            model.addAttribute("message", "book not found");
            model.addAttribute("books", booksService.listAllBooks());
            return "BooksHTML/ListBooks";
        }
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute BooksDTO book, RedirectAttributes redirectAttributes) {
        if (book.getId() != null) {
            booksService.updateBookById(book.getId(), book);
            redirectAttributes.addFlashAttribute("message", "Book updated successfully");
        } else {
            booksService.createBook(book);
            redirectAttributes.addFlashAttribute("message", "Book registered successfully");
        }
        return "redirect:/books/ui/list";
    }

    @GetMapping("/add")
    public String addBook (Model model) {
        model.addAttribute("book", new BooksDTO());
        model.addAttribute("allBooks", booksService.listAllBooks());
        return "BooksHTML/AddBook";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        BooksDTO book = booksService.listBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("allBooks", booksService.listAllBooks());
            return "BooksHTML/AddBook";
        }
        return "redirect:/books/ui/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookById(@PathVariable Long id) {
        booksService.deleteBookById(id);
        return "redirect:/books/ui/list";
    }

}
