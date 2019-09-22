package com.rentingbook.api.service;

import com.rentingbook.api.model.book.bookdetails.Author;

import java.util.List;

public interface AuthorService {

    List<Author> createAuthors(List<Author> authors);

    List<Author> deleteAuthors(List<Integer> ids);

    List<Author> updateAuthors(List<Author> authors);

    Author findByID(int id);
}
