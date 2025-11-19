package dev.cesar.LibrarySystem.Readers;

import dev.cesar.LibrarySystem.Books.BooksModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_readers")
public class ReadersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    @OneToMany(mappedBy = "reader")
    private BooksModel books;

    public ReadersModel() {
    }

    public ReadersModel(String name, String email, int age, BooksModel books) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BooksModel getBooks() {
        return books;
    }

    public void setBooks(BooksModel books) {
        this.books = books;
    }
}
