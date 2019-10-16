package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.book.bookdetails.Genre;
import com.rentingbook.api.model.book.bookdetails.Language;
import com.rentingbook.api.service.RentalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rentingbook")
public class RentalBookController {
    private RentalBookService service;

    @Autowired
    public void setService(RentalBookService service) {
        this.service = service;
    }

    @PostMapping
    public List<RentalBook> createBooks(@RequestBody List<RentalBook> rentalBooks) {
        return service.createBooks(rentalBooks);
    }

    @GetMapping
    public List<RentalBook> getBooks(
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

        List<RentalBook> results = service.findAll().stream()
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
                    if (title != null) {
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
                        return rentingBook.getRentalPrice() >= startPrice;
                    }
                    return true;
                })
                .filter(rentingBook -> {
                    if (endPrice != null) {
                        return rentingBook.getRentalPrice() >= endPrice;
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
        List<RentalBook> responsePage = new ArrayList<>();
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
    public RentalBook getBook(@PathVariable String barcode) {
        return service.findByBarcode(barcode);
    }
}
