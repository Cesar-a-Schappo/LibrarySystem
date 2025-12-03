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

    public List<BooksDTO> listAllBooks() {
        List<BooksModel> books = booksRepository.findAll();
        return books.stream()
                .map(booksMapper::map)
                .toList();
    }

    public BooksDTO listBookById(Long id) {
        Optional<BooksModel> listById = booksRepository.findById(id);
        return listById.map(booksMapper::map).orElse(null);
    }

    public BooksDTO updateBookById(Long id, BooksDTO book) {
        Optional<BooksModel> bookToUpdate = booksRepository.findById(id);
        if (bookToUpdate.isPresent()) {
            BooksModel updatedBook = booksMapper.map(book);
            updatedBook.setId(id);
            BooksModel savedBook = booksRepository.save(updatedBook);
            return booksMapper.map(savedBook);
        }
        return null;
    }

    public void deleteBookById(Long id) {
        booksRepository.deleteById(id);
    }

}
