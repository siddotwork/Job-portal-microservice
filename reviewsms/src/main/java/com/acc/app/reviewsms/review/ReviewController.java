package com.acc.app.reviewsms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<?> getAllReviews(@RequestParam Long companyId) {
        return service.getAllReviews(companyId).isEmpty() ?
                new ResponseEntity<>("No reviews found", HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(service.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        if (review != null) {
            service.createReview(companyId, review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>("unable to add entry.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById( @PathVariable Long reviewId) {
        return new ResponseEntity<>(service.getReviewById(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReviewById(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean isReviewUpdated = service.updateReviewById(review,reviewId);
        return isReviewUpdated ? new ResponseEntity<>("update successfully", HttpStatus.OK) :
                new ResponseEntity<>("failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteById(@PathVariable Long reviewId) {
        return service.deleteReviewById(reviewId) ?
                new ResponseEntity<>("review with id " + reviewId + " deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("Unable to delete review", HttpStatus.BAD_REQUEST);
    }

}
