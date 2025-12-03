package dev.cesar.LibrarySystem.Books;

import dev.cesar.LibrarySystem.Readers.ReadersModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksDTO {

    private Long id;
    private String title;
    private String genre;
    private String author;
    private ReadersModel reader;

}
