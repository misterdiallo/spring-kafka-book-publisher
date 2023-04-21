package com.misterdiallo.backend.authormicroservice.service;

import com.misterdiallo.backend.authormicroservice.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Author save(Author book);
    Page<Author> listAuthors(Pageable pageable);
}