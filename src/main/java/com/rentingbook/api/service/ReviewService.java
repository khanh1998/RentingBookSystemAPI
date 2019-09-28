package com.rentingbook.api.service;

import com.rentingbook.api.model.book.bookdetails.Review;

public interface ReviewService {
    Review save(Review review);

    Review delete(int id);

    Review get(int id);
}
