package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.book.bookdetails.Genre;
import com.rentingbook.api.model.book.bookdetails.Language;
import com.rentingbook.api.service.RentingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rentingbook")
public class RentingBookController {
    private RentingBookService service;

    @Autowired
    public void setService(RentingBookService service) {
        this.service = service;
    }

    @PostMapping
    public List<RentingBook> createBooks(@RequestBody List<RentingBook> rentingBooks) {
        return service.createBooks(rentingBooks);
    }

    @GetMapping
    public List<RentingBook> getBooks(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer authorId,
            @RequestParam(required = false) String barcode,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Float startPrice,
            @RequestParam(required = false) Float endPrice) {
        System.out.println(authorId);
        System.out.println(barcode);
        System.out.println(isbn);
        System.out.println(genre);
        System.out.println(language);
        System.out.println(startPrice);
        System.out.println(endPrice);

        List<RentingBook> results = service.findAll().stream()
                .filter(rentingBook -> {
                    if (isbn != null) {
                        return rentingBook.getBook().getIsbn().equalsIgnoreCase(isbn);
                    }
                    return true;
                })
                .filter(rentingBook -> {
                    if (barcode != null) {
                        return rentingBook.getBarcode().equalsIgnoreCase(barcode);
                    }
                    return true;
                })
                .filter(rentingBook -> {
                    if (barcode != null) {
                        return rentingBook.getBook().getTitle().toLowerCase().contains(title.toLowerCase());
                    }
                    return true;
                })
                .filter(rentingBook -> rentingBook
                        .getBook()
                        .getAuthors()
                        .stream()
                        .anyMatch(author -> {
                            if (authorId != null) {
                                return author.getId() == authorId;
                            }
                            return true;
                        }))

                .filter(rentingBook -> {
                    if (genre != null) {
                        return rentingBook.getBook().getGenres().stream().anyMatch(genre1 -> genre1 == genre);
                    }
                    return true;
                })
                .filter(rentingBook -> {
                    if (startPrice != null) {
                        return rentingBook.getPrice() >= startPrice;
                    }
                    return true;
                })
                .filter(rentingBook -> {
                    if (endPrice != null) {
                        return rentingBook.getPrice() >= endPrice;
                    }
                    return true;
                })
                .filter(rentingBook -> {
                    if (language != null) {
                        return rentingBook.getBook().getLanguage().equals(language);
                    }
                    return true;
                })
                .collect(Collectors.toList());
        List<RentingBook> responsePage = new ArrayList<>();
        int start = page * size;
        int stop = page * size + size - 1;
        if (results.size() > start) {
            if (results.size() < (stop + 1)) {
                stop = results.size() - 1;
            }
            for (int i = start; i <= stop; i++) {
                if (results.get(i) != null)
                    responsePage.add(results.get(i));
            }
        }
        return responsePage;


    }

    @GetMapping("/{barcode}")
    public RentingBook getBook(@PathVariable String barcode) {
        return service.findByBarcode(barcode);
    }
}
