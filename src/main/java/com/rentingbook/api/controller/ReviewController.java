package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.book.bookdetails.Review;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.RentalBookService;
import com.rentingbook.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentingbook/review")
public class ReviewController {
    private ReviewService reviewService;
    private RentalBookService bookService;
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setBookService(RentalBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Review createReview(@RequestParam String barcode, @RequestBody Review review) {
        review.setAccount(accountService.getCurrentAccount().getUsername());
        Review createdReview = reviewService.save(review);
        RentalBook rentalBook = bookService.findByBarcode(barcode);
        List<Review> reviews = rentalBook.getReviews();
        reviews.add(createdReview);
        rentalBook.setReviews(reviews);
        bookService.save(rentalBook);
        return createdReview;
    }

    @GetMapping
    public List<Review> getReviewsOfRentingBook(@RequestParam String barcode) {
        return bookService.findByBarcode(barcode).getReviews();
    }
}
