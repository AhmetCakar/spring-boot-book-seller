package com.sha.spring_boot_book_seller.service;

import com.sha.spring_boot_book_seller.model.Book;

import java.util.List;

public interface IBookService {

    Book saveBook(Book book);

    void deleteBook(Long id);

    List<Book> findAllBooks();
}
