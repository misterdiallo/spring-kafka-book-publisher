package com.misterdiallo.backend.bookmicroservice.service;


import com.misterdiallo.backend.bookmicroservice.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BooksService {
    Book save(Book book);

    Page<Book> listBooks(Pageable pagable);

}