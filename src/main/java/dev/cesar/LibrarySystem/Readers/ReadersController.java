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
    public ReadersModel createReader(@RequestBody ReadersModel reader) {
        return readersService.createReader(reader);
    }

    @GetMapping("/list")
    public List<ReadersModel> listAllReaders() {
        return readersService.listAllReaders();
    }

    @GetMapping("/list/{id}")
    public ReadersModel listReadersById(@PathVariable Long id) {
        return readersService.listReadersById(id);
    }

    @PutMapping("/update/{id}")
    public ReadersModel updateReaderById(@PathVariable Long id, @RequestBody ReadersModel reader) {
        return readersService.updateReaderById(id, reader);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReaderById(@PathVariable Long id) {
        readersService.deleteReaderById(id);
    }


}
