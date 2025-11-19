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
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    @Column(name = "reader")
    private List<ReadersModel> reader;

}
