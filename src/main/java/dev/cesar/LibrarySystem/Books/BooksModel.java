package dev.cesar.LibrarySystem.Books;

import dev.cesar.LibrarySystem.Readers.ReadersModel;
import jakarta.persistence.*;

import java.util.List;

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

    public BooksModel() {
    }

    public BooksModel(String title, String genre, String author, List<ReadersModel> reader) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ReadersModel> getReader() {
        return reader;
    }

    public void setReader(List<ReadersModel> reader) {
        this.reader = reader;
    }
}
