package dev.cesar.LibrarySystem.Readers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("readers")
public class ReadersController {

    private final ReadersService readersService;

    public ReadersController(ReadersService readersService) {
        this.readersService = readersService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Essa é minha primeira mensagem nesta rota";
    }

    @PostMapping("/create")
    public String createReader() {
        return "reader created";
    }

    @GetMapping("/list")
    public List<ReadersModel> listAllReaders() {
        return readersService.listAllReaders();
    }

    @GetMapping("/list/{id}")
    public ReadersModel listReadersById(@PathVariable Long id) {
        return readersService.listReadersById(id);
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
