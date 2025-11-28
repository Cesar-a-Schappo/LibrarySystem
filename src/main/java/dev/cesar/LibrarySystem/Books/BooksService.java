package dev.cesar.LibrarySystem.Books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BooksModel> listAllBooks() {
        return booksRepository.findAll();
    }

}
