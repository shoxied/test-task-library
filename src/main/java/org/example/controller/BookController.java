package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/myRest")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "addNewBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addNewBook(@RequestBody BookDto bookDto){
        return bookService.addNewBook(bookDto);
    }

    @PatchMapping(value = "addNewBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book editBook(@PathVariable Integer id,
                         @RequestBody Map<String, Object> updates) throws Exception {
        return bookService.editBook(id, updates);
    }
}
