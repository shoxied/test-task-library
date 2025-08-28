package org.example.service;

import org.example.dto.BookDto;
import org.example.dto.TakenBookDto;
import org.example.entity.Book;
import org.example.entity.TakenBook;

import java.util.List;
import java.util.Map;

public interface BookService {
    Book addNewBook(BookDto bookDto);
    Book editBook(int id, Map<String, Object> updates) throws Exception;
    List<Book> getBooks();
    TakenBook takeBook(int bookId, int clientId);
    List<TakenBookDto> getTakenBooks();

}
