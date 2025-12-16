package dev.cesar.LibrarySystem.Readers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a new reader", description = "This route creates a new reader and inserts it on the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "reader successfully created"),
            @ApiResponse(responseCode = "400", description = "reader creation error")
    })
    public ResponseEntity<String> createReader(
            @Parameter(description = "user send the data of the reader that will be created on the path request") @RequestBody ReadersDTO reader) {
        ReadersDTO newReader = readersService.createReader(reader);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reader successfully created: " + newReader.getName() + ". ID: " + newReader.getId());
    }

    @GetMapping("/list")
    @Operation(summary = "List all readers", description = "This route lists all the readers in the database")
    @ApiResponse(responseCode = "200", description = "readers listed")
    public ResponseEntity<List<ReadersDTO>> listAllReaders() {
        List<ReadersDTO> readers = readersService.listAllReaders();
        return ResponseEntity.ok(readers);
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "List a reader by ID", description = "This route lists a reader by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reader listed"),
            @ApiResponse(responseCode = "404", description = "reader not found")
    })
    public ResponseEntity<?> listReadersById(
            @Parameter(description = "user sends the ID of the reader that will be listed on the path request") @PathVariable Long id) {
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
    @Operation(summary = "Update a reader data", description = "This route gets a reader by its ID and updates its data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reader updated"),
            @ApiResponse(responseCode = "404", description = "reader not found. data not updated")
    })
    public ResponseEntity<String> updateReaderById(
            @Parameter(description = "user sends the ID of the reader that will be updated on the path request") @PathVariable Long id,
            @Parameter(description = "user sends the reader data to be updated on the request body") @RequestBody ReadersDTO reader) {
        ReadersDTO updatedReader = readersService.updateReaderById(id, reader);
        if (updatedReader != null) {
            return ResponseEntity.ok("Reader of ID: " + id + " updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reader of ID: " + id + " not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a reader", description = "This route gets a reader by its ID and deletes it from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reader deleted"),
            @ApiResponse(responseCode = "404", description = "reader not found. reader not deleted")
    })
    public ResponseEntity<String> deleteReaderById(
            @Parameter(description = "user sends the ID of the reader that will be deleted on the path request") @PathVariable Long id) {
        if (readersService.listReadersById(id) != null) {
            readersService.deleteReaderById(id);
            return ResponseEntity.ok("Reader of ID: " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reader of ID: " + id + " not found.");
        }
    }
}
