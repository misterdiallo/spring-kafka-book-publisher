package com.misterdiallo.backend.bookmicroservice.controller;



import com.misterdiallo.backend.bookmicroservice.domain.Book;
import com.misterdiallo.backend.bookmicroservice.service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BooksService booksService;

    public BookController(final BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(path="/books")
    public Page<Book> listBooks(final Pageable pagable) {
        return booksService.listBooks(pagable);
    }

}