package dev.cesar.LibrarySystem.Readers;

import dev.cesar.LibrarySystem.Books.BooksModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "tb_readers")
public class ReadersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "reader")
    @Column(name = "books")
    private BooksModel books;

}
