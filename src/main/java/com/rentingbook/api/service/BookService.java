package com.rentingbook.api.service;

import com.rentingbook.api.model.book.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> createBooks(List<Book> books);

    List<Book> deleteBooks(List<String> bookIDs);

    List<Book> updateBooks(List<Book> books);

    List<Book> findByPage(Pageable pageable);

    Book findByID(String ISBN);
}
