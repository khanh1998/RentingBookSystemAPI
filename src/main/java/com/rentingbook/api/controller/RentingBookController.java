package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.service.RentingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<RentingBook> getBooks(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findByPage(pageable);
    }

    @GetMapping("/{barcode}")
    public RentingBook getBook(@PathVariable String barcode) {
        return service.findByBarcode(barcode);
    }
}
