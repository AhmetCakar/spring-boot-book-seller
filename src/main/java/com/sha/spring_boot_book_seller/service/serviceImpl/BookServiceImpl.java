package com.sha.spring_boot_book_seller.service.serviceImpl;

import com.sha.spring_boot_book_seller.model.Book;
import com.sha.spring_boot_book_seller.repository.IBookRepository;
import com.sha.spring_boot_book_seller.service.IBookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    public BookServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        book.setCreateTime(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
