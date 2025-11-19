package dev.cesar.LibrarySystem.Books;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_books", uniqueConstraints = {@UniqueConstraint(columnNames = {"author", "title"})})
@Getter
@Setter
public class BooksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String author;

}
