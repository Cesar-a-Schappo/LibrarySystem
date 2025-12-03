package dev.cesar.LibrarySystem.Books;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;
    private final BooksMapper booksMapper;

    public BooksService(BooksRepository booksRepository, BooksMapper booksMapper) {
        this.booksRepository = booksRepository;
        this.booksMapper = booksMapper;
    }

    public BooksDTO createBook(BooksDTO bookDTO) {
        BooksModel book = booksMapper.map(bookDTO);
        book = booksRepository.save(book);
        return booksMapper.map(book);
    }

    public List<BooksModel> listAllBooks() {
        return booksRepository.findAll();
    }

    public BooksModel listBookById(Long id) {
        Optional<BooksModel> listById = booksRepository.findById(id);
        return listById.orElse(null);
    }

    public BooksModel updateBookById(Long id, BooksModel book) {
        if (booksRepository.existsById(id)) {
            book.setId(id);
            booksRepository.save(book);
        }
        return null;
    }

    public void deleteBookById(Long id) {
        booksRepository.deleteById(id);
    }

}
