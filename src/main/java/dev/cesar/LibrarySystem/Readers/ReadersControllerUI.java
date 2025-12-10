package dev.cesar.LibrarySystem.Readers;

import dev.cesar.LibrarySystem.Books.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("readers/ui")
public class ReadersControllerUI {

    private final ReadersService readersService;
    private final BooksService booksService;

    public ReadersControllerUI(ReadersService readersService, BooksService booksService) {
        this.readersService = readersService;
        this.booksService = booksService;
    }

    @GetMapping("/list")
    public String listAllReaders(Model model) {
        List<ReadersDTO> readers = readersService.listAllReaders();
        model.addAttribute("readers", readers);
        return "ReadersHTML/ListReaders";
    }

    @GetMapping("/list/{id}")
    public String listReadersById(@PathVariable Long id, Model model) {
        ReadersDTO reader = readersService.listReadersById(id);
        model.addAttribute("reader", reader);
        if (reader != null) {
            return "ReadersHTML/ReaderDetails";
        } else {
            model.addAttribute("message", "reader not found");
            model.addAttribute("readers", readersService.listAllReaders());
            return "ReadersHTML/ListReaders";
        }
    }

    @GetMapping("/add")
    public String addReader (Model model) {
        model.addAttribute("reader", new ReadersDTO());
        model.addAttribute("allBooks", booksService.listAllBooks());
        return "ReadersHTML/AddReader";
    }

    @PostMapping("/save")
    public String saveReader(@ModelAttribute ReadersDTO reader, @RequestParam(required = false) List<Long> bookIds, RedirectAttributes redirectAttributes) {
        readersService.saveReaderWithBooks(reader, bookIds);
        redirectAttributes.addFlashAttribute("message", "Reader saved successfully");
        return "redirect:/readers/ui/list";
    }

    @GetMapping("/edit/{id}")
    public String editReader(@PathVariable Long id, Model model) {
        ReadersDTO reader = readersService.listReadersById(id);
        if (reader != null) {
            model.addAttribute("reader", reader);
            model.addAttribute("allBooks", booksService.listAllBooks());
            return "ReadersHTML/AddReader";
        }
        return "redirect:/readers/ui/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteReaderById(@PathVariable Long id) {
        readersService.deleteReaderById(id);
        return "redirect:/readers/ui/list";
    }

}
