package dev.cesar.LibrarySystem.Readers;

import dev.cesar.LibrarySystem.Books.BooksModel;
import dev.cesar.LibrarySystem.Books.BooksRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReadersService {

    private final ReadersRepository readersRepository;
    private final ReadersMapper readersMapper;
    private final BooksRepository booksRepository;

    public ReadersService(ReadersRepository readersRepository, ReadersMapper readersMapper, BooksRepository booksRepository) {
        this.readersRepository = readersRepository;
        this.readersMapper = readersMapper;
        this.booksRepository = booksRepository;
    }

    public ReadersDTO createReader(ReadersDTO readerDTO) {
        ReadersModel reader = readersMapper.map(readerDTO);
        reader = readersRepository.save(reader);
        return readersMapper.map(reader);
    }

    public void saveReaderWithBooks(ReadersDTO readerDTO, List<Long> bookIds) {
        ReadersModel readerModel = readersMapper.map(readerDTO);
        ReadersModel savedReader = readersRepository.save(readerModel);
        if (bookIds != null && !bookIds.isEmpty()) {
            List<BooksModel> selectedBooks = booksRepository.findAllById(bookIds);
            for (BooksModel book : selectedBooks) {
                book.setReader(savedReader);
            }
            booksRepository.saveAll(selectedBooks);
        }
    }

    public List<ReadersDTO> listAllReaders() {
        List<ReadersModel> readers = readersRepository.findAll();
        return readers.stream()
                .map(readersMapper::map)
                .toList();
    }

    public ReadersDTO listReadersById(Long id) {
        Optional<ReadersModel> listById = readersRepository.findById(id);
        return listById.map(readersMapper::map).orElse(null);
    }

    public ReadersDTO updateReaderById(Long id, ReadersDTO reader) {
        Optional<ReadersModel> readerToUpdate = readersRepository.findById(id);
        if (readerToUpdate.isPresent()) {
            ReadersModel updatedReader = readersMapper.map(reader);
            updatedReader.setId(id);
            ReadersModel savedReader = readersRepository.save(updatedReader);
            return readersMapper.map(savedReader);
        }
        return null;
    }

    public void deleteReaderById(Long id) {
        Optional<ReadersModel> readerToDelete = readersRepository.findById(id);
        if (readerToDelete.isPresent()) {
            ReadersModel reader = readerToDelete.get();
            List<BooksModel> books = reader.getBooks();
            if (books != null) {
                for (BooksModel book:books) {
                    book.setReader(null);
                }
                booksRepository.saveAll(books);
            }
            readersRepository.deleteById(id);
        }
    }

}
