package dev.cesar.LibrarySystem.Readers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("readers")
public class ReadersController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Essa é minha primeira mensagem nesta rota";
    }

    @PostMapping("/create")
    public String createReader() {
        return "reader created";
    }

    @GetMapping("/list")
    public String listAllReaders() {
        return "all readers listed";
    }

    @GetMapping("/list/{id}")
    public String listReadersById() {
        return "reader listed by its id";
    }

    @PutMapping("/change/{id}")
    public String changeReaderById() {
        return "reader changed by its id";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReaderById() {
        return "reader deleted by its id";
    }


}
