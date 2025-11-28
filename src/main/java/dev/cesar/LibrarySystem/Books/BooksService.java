package dev.cesar.LibrarySystem.Books;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BooksModel createBook(BooksModel book) {
        return booksRepository.save(book);
    }

    public List<BooksModel> listAllBooks() {
        return booksRepository.findAll();
    }

    public BooksModel listBookById(Long id) {
        Optional<BooksModel> listById = booksRepository.findById(id);
        return listById.orElse(null);
    }

    public void deleteBookById(Long id) {
        booksRepository.deleteById(id);
    }

}
