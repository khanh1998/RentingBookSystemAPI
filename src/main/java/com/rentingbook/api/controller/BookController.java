package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.Book;
import com.rentingbook.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService service;

    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Book> getAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findByPage(pageable);
    }

    @PostMapping("")
    public List<Book> create(@RequestBody List<Book> books) {
        System.out.println("\nbooks:\n" + books);
        return service.createBooks(books);
    }

    @DeleteMapping("")
    public List<Book> delete(@RequestBody List<String> ids) {
        return service.deleteBooks(ids);
    }
}
