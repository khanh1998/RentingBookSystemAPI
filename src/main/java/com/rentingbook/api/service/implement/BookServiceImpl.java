package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.Book;
import com.rentingbook.api.repository.BookRepository;
import com.rentingbook.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private BookRepository repository;

    @Autowired
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> createBooks(List<Book> books) {
        return repository.saveAll(books);
    }

    @Override
    public List<Book> deleteBooks(List<String> bookIDs) {
        List<Book> books = new ArrayList<>();
        bookIDs.forEach(bookID -> {
            Optional<Book> book = repository.findById(bookID);
            if (book.isPresent()) {
                books.add(book.get());
                repository.deleteById(bookID);
            }
        });
        return books;
    }

    @Override
    public List<Book> updateBooks(List<Book> books) {
        List<Book> updatedBooks = new ArrayList<>();
        books.forEach(book -> {
            Optional<Book> createdBook = repository.findById(book.getIsbn());
            if (createdBook.isPresent()) {
                updatedBooks.add(createdBook.get());
                repository.save(createdBook.get());
            }
        });
        return updatedBooks;
    }

    @Override
    public List<Book> findByPage(Pageable pageable) {
        Page<Book> books = repository.findAll(pageable);
        List<Book> results = new ArrayList<>();
        books.forEach(book -> {
            results.add(book);
        });
        return results;
    }

    @Override
    public Book findByISBN(String ISBN) {
        return repository.findByIsbn(ISBN);
    }
}
