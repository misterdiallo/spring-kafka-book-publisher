package com.misterdiallo.backend.authormicroservice.service.impl;

import com.misterdiallo.backend.authormicroservice.domain.Author;
import com.misterdiallo.backend.authormicroservice.repository.AuthorRepository;
import com.misterdiallo.backend.authormicroservice.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository bookRepository) {
        this.authorRepository = bookRepository;
    }

    @Override
    public Author save(final Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Page<Author> listAuthors(final Pageable pageable){
        return authorRepository.findAll(pageable);
    }


}