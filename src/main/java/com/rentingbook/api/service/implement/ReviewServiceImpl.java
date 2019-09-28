package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.bookdetails.Review;
import com.rentingbook.api.repository.ReviewRepository;
import com.rentingbook.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review delete(int id) {
        Review review = this.get(id);
        if (review != null) {
            reviewRepository.delete(review);
            return review;
        }
        return null;
    }

    @Override
    public Review get(int id) {
        return reviewRepository.findById(id).get();
    }
}
