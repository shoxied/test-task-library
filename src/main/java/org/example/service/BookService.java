package org.example.service;

import org.example.dto.BookDto;
import org.example.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface BookService {
    Book addNewBook(BookDto bookDto);
    Book editBook(int id, Map<String, Object> updates) throws Exception;
    List<Book> getBooks();

}
