package dev.cesar.LibrarySystem.Books;

import dev.cesar.LibrarySystem.Readers.ReadersModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "tb_books")
public class BooksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String author;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private List<ReadersModel> reader;

}
