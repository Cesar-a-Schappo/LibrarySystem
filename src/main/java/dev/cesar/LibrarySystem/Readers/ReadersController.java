package dev.cesar.LibrarySystem.Readers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("readers")
public class ReadersController {

    private final ReadersService readersService;

    public ReadersController(ReadersService readersService) {
        this.readersService = readersService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createReader(@RequestBody ReadersDTO reader) {
        ReadersDTO newReader = readersService.createReader(reader);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reader successfully created: " + newReader.getName() + ". ID: " + newReader.getId());
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReadersDTO>> listAllReaders() {
        List<ReadersDTO> readers = readersService.listAllReaders();
        return ResponseEntity.ok(readers);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> listReadersById(@PathVariable Long id) {
        ReadersDTO reader = readersService.listReadersById(id);
        if (reader != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(reader);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reader of ID: " + id + " not found.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateReaderById(@PathVariable Long id, @RequestBody ReadersDTO reader) {
        ReadersDTO updatedReader = readersService.updateReaderById(id, reader);
        if (updatedReader != null) {
            return ResponseEntity.ok("Reader of ID: " + id + " updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reader of ID: " + id + " not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReaderById(@PathVariable Long id) {
        if (readersService.listReadersById(id) != null) {
            readersService.deleteReaderById(id);
            return ResponseEntity.ok("Reader of ID: " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reader of ID: " + id + " not found.");
        }
    }
}
