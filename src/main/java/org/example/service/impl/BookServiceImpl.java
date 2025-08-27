package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.repo.BookRepository;
import org.example.service.BookService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book addNewBook(BookDto bookDto) {
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .isbn(bookDto.getIsbn())
                .build();

        return bookRepository.save(book);
    }

    @Override
    public Book editBook(int id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book with id " + id + " not found"));

        updates.forEach((field, value) -> {
            switch (field) {
                case "title":
                    book.setTitle((String) value);
                    break;
                case "author":
                    book.setAuthor((String) value);
                    break;
                case "isbn":
                    book.setIsbn((int) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field " + field + " not supported");
            }
        });

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
