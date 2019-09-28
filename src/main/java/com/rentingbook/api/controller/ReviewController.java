package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.book.bookdetails.Review;
import com.rentingbook.api.service.RentingBookService;
import com.rentingbook.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentingbook/review")
public class ReviewController {
    private ReviewService reviewService;
    private RentingBookService bookService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setBookService(RentingBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Review createReview(@RequestParam String barcode, @RequestBody Review review) {
        Review createdReview = reviewService.save(review);
        RentingBook rentingBook = bookService.findByBarcode(barcode);
        List<Review> reviews = rentingBook.getReviews();
        reviews.add(createdReview);
        rentingBook.setReviews(reviews);
        bookService.save(rentingBook);
        return createdReview;
    }

    @GetMapping
    public List<Review> getReviewsOfRentingBook(@RequestParam String barcode) {
        return bookService.findByBarcode(barcode).getReviews();
    }
}
