package dev.cesar.LibrarySystem.Readers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadersService {

    private final ReadersRepository readersRepository;

    public ReadersService(ReadersRepository readersRepository) {
        this.readersRepository = readersRepository;
    }

    public List<ReadersModel> listAllReaders() {
        return readersRepository.findAll();
    }
}
