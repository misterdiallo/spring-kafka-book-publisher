package com.misterdiallo.backend.bookmicroservice.service.impl;

import com.misterdiallo.backend.bookmicroservice.domain.Book;
import com.misterdiallo.backend.bookmicroservice.repository.BookRepository;
import com.misterdiallo.backend.bookmicroservice.service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;

    public BooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    public Page<Book> listBooks(final Pageable pagable) {
        return bookRepository.findAll(pagable);
    }

}