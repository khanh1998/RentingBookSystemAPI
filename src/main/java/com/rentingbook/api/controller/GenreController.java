package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.bookdetails.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @GetMapping
    public List<Genre> getGenreList() {
        return new ArrayList<>(Arrays.asList(Genre.values()));
    }
}
