package dev.cesar.LibrarySystem.Readers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_readers", uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "email"})
        }
)
@Getter
@Setter
public class ReadersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
}
