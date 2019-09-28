package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.bookdetails.Publisher;
import com.rentingbook.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private PublisherService publisherService;

    @PostMapping
    public List<Publisher> createPublishers(@RequestBody List<Publisher> publishers) {
        return publisherService.createPublishers(publishers);
    }

    @GetMapping
    public Publisher getPublishers(@RequestParam int id) {
        return publisherService.findByID(id);
    }

    @Autowired
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
}
