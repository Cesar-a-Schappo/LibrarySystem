package dev.cesar.LibrarySystem.Books;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BooksModel, Long> {
}
