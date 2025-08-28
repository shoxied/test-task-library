package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.dto.TakenBookDto;
import org.example.entity.Book;
import org.example.entity.TakenBook;
import org.example.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "addNewBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addNewBook(@RequestBody BookDto bookDto){
        return bookService.addNewBook(bookDto);
    }

    @PatchMapping(value = "editBook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book editBook(@PathVariable Integer id,
                         @RequestBody Map<String, Object> updates) throws Exception {
        return bookService.editBook(id, updates);
    }

    @GetMapping(value = "getBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping(value = "takeBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public TakenBook takeBook(@RequestParam Integer bookId,
                              @RequestParam Integer clientId){
        return bookService.takeBook(bookId, clientId);
    }

    @GetMapping(value = "getTakenBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TakenBookDto> getTakenBooks(){
        return bookService.getTakenBooks();
    }
}
