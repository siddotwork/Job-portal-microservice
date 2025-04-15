package com.acc.app.reviewsms.review;

import java.util.List;

public interface ReviewService {
    List<Review>getAllReviews(Long companyId);
    Review createReview(Long companyId,Review review);
    Review getReviewById(Long reviewId);
    boolean updateReviewById(Review review,  Long reviewId);
    boolean deleteReviewById( Long reviewId);
}
