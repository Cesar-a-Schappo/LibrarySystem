package dev.cesar.LibrarySystem.Books;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_books")
public class BooksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String author;

    public BooksModel() {
    }

    public BooksModel(String title, String genre, String author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
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
}
