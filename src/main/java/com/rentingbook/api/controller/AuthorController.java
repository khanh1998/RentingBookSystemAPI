package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public Author getAuthors(@RequestParam int id) {
        return authorService.findByID(id);
    }

    @PostMapping("")
    public List<Author> createAuthors(@RequestBody List<Author> authors) {
        return authorService.createAuthors(authors);
    }
}
