package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<Author> getAuthors(@RequestParam int id) {
        Optional<Author> author = authorService.findByID(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("")
    public List<Author> createAuthors(@RequestBody List<Author> authors) {
        return authorService.createAuthors(authors);
    }
}
