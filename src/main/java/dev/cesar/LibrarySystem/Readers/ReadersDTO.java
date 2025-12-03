package dev.cesar.LibrarySystem.Readers;

import dev.cesar.LibrarySystem.Books.BooksModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadersDTO {

    private Long id;
    private String name;
    private String email;
    private int age;
    private String address;
    private List<BooksModel> books;

}
