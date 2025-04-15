package com.acc.app.reviewsms.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = repository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review createReview(Long companyId, Review review) {

        if (companyId != null&&review!=null) {
            review.setCompanyId(companyId);
            repository.save(review);
            return review;
        }
        return null;

    }

    @Override
    public Review getReviewById(Long reviewId) {
        Review review=repository.findById(reviewId).orElse(null);
        return review;
    }

    @Override
    public boolean updateReviewById(Review updatedReview, Long reviewId) {
        Review review=repository.findById(reviewId).orElse(null);
        if (review!=null) {
            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());
            review.setDescription(updatedReview.getDescription());
            review.setCompanyId(updatedReview.getCompanyId());
            repository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviewById(Long reviewId) {
       Review review=repository.findById(reviewId).orElse(null);
       if (review!=null){
           repository.deleteById(reviewId);
           return true;
       }
       return false;
    }
}