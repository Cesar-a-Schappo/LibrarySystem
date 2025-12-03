package dev.cesar.LibrarySystem.Books;

import org.springframework.stereotype.Component;

@Component
public class BooksMapper {

    public BooksModel map(BooksDTO booksDTO) {
        BooksModel booksModel = new BooksModel();
        booksModel.setId(booksDTO.getId());
        booksModel.setTitle(booksDTO.getTitle());
        booksModel.setGenre(booksDTO.getGenre());
        booksModel.setAuthor(booksDTO.getAuthor());
        booksModel.setReader(booksDTO.getReader());

        return booksModel;
    }

    public BooksDTO map(BooksModel booksModel) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setId(booksModel.getId());
        booksDTO.setTitle(booksModel.getTitle());
        booksDTO.setGenre(booksModel.getGenre());
        booksDTO.setAuthor(booksModel.getAuthor());
        booksDTO.setReader(booksModel.getReader());

        return booksDTO;
    }

}
