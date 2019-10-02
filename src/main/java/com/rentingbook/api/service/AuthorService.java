package com.rentingbook.api.service;

import com.rentingbook.api.model.book.bookdetails.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> createAuthors(List<Author> authors);

    List<Author> deleteAuthors(List<Integer> ids);

    List<Author> updateAuthors(List<Author> authors);

    Optional<Author> findByID(int id);
}
