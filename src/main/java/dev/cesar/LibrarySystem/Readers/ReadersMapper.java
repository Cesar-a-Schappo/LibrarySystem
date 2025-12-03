package dev.cesar.LibrarySystem.Readers;

import org.springframework.stereotype.Component;

@Component
public class ReadersMapper {

    public ReadersModel map(ReadersDTO readersDTO) {
        ReadersModel readersModel = new ReadersModel();
        readersModel.setId(readersDTO.getId());
        readersModel.setName(readersDTO.getName());
        readersModel.setEmail(readersDTO.getEmail());
        readersModel.setAge(readersDTO.getAge());
        readersModel.setAddress(readersDTO.getAddress());
        readersModel.setBooks(readersDTO.getBooks());

        return readersModel;
    }

    public ReadersDTO map(ReadersModel readersModel) {
        ReadersDTO readersDTO = new ReadersDTO();
        readersDTO.setId(readersModel.getId());
        readersDTO.setName(readersModel.getName());
        readersDTO.setEmail(readersModel.getEmail());
        readersDTO.setAge(readersModel.getAge());
        readersDTO.setAddress(readersModel.getAddress());
        readersDTO.setBooks(readersModel.getBooks());

        return readersDTO;
    }

}
