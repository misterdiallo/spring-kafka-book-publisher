package com.misterdiallo.backend.authormicroservice.repository;

import com.misterdiallo.backend.authormicroservice.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface AuthorRepository extends CrudRepository<Author, String>,
        PagingAndSortingRepository<Author, String> {
}
