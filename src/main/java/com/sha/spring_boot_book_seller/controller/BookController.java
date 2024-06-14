package com.sha.spring_boot_book_seller.controller;

import com.sha.spring_boot_book_seller.model.Book;
import com.sha.spring_boot_book_seller.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {


        return new ResponseEntity<>(bookService.saveBook(book),HttpStatus.CREATED);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }
}
