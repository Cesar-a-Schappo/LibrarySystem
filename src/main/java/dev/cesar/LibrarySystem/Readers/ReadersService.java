package dev.cesar.LibrarySystem.Readers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadersService {

    private final ReadersRepository readersRepository;

    public ReadersService(ReadersRepository readersRepository) {
        this.readersRepository = readersRepository;
    }

    public ReadersModel createReader(ReadersModel reader) {
        return readersRepository.save(reader);
    }

    public List<ReadersModel> listAllReaders() {
        return readersRepository.findAll();
    }

    public ReadersModel listReadersById(Long id) {
        Optional<ReadersModel> listById = readersRepository.findById(id);
        return listById.orElse(null);
    }

    public ReadersModel updateReaderById(Long id, ReadersModel reader) {
        if (readersRepository.existsById(id)) {
            reader.setId(id);
            return readersRepository.save(reader);
        }
        return null;
    }

    public void deleteReaderById(Long id) {
        readersRepository.deleteById(id);
    }

}
