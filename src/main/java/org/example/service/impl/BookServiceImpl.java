package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.dto.TakenBookDto;
import org.example.entity.Book;
import org.example.entity.TakenBook;
import org.example.entity.Client;
import org.example.repo.BookRepository;
import org.example.repo.ClientRepository;
import org.example.repo.TakenBookRepository;
import org.example.service.BookService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final TakenBookRepository takenBookRepository;

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

    @Override
    public TakenBook takeBook(int bookId, int clientId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("book with id " + bookId + " not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("client with id " + clientId + " not found"));

        TakenBook takenBook = TakenBook.builder()
                .book(book)
                .client(client)
                .issue_date(OffsetDateTime.now())
                .build();

        return takenBookRepository.save(takenBook);
    }

    @Override
    public List<TakenBookDto> getTakenBooks() {
        List<TakenBook> takenBooks = takenBookRepository.findAll();

        List<TakenBookDto> takenBookDtos = takenBooks.stream().map(takenBook -> {
            TakenBookDto takenBookDto = new TakenBookDto();
            takenBookDto.setName(takenBook.getClient().getName());
            takenBookDto.setSurname(takenBook.getClient().getSurname());
            takenBookDto.setPatronymic(takenBook.getClient().getPatronymic());
            takenBookDto.setBirthDate(takenBook.getClient().getBirth_date());
            takenBookDto.setTitle(takenBook.getBook().getTitle());
            takenBookDto.setAuthor(takenBook.getBook().getAuthor());
            takenBookDto.setIsbn(takenBook.getBook().getIsbn());
            takenBookDto.setIssueDate(takenBook.getIssue_date());
            return takenBookDto;
                }).collect(Collectors.toList());

        return takenBookDtos;
    }
}
