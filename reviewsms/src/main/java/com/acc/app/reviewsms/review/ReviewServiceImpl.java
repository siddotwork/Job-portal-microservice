package com.acc.app.reviewsms.review;


import com.acc.app.reviewsms.review.dto.ReviewAndCompanyDTO;
import com.acc.app.reviewsms.review.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private ReviewAndCompanyDTO convertToDTO(Company company){
        ReviewAndCompanyDTO jobWithCompanyDTO=new ReviewAndCompanyDTO();
        jobWithCompanyDTO.setCompany(company);
        RestTemplate restTemplate=new RestTemplate();
        Company company1= restTemplate.getForObject("http://localhost:8083/companies/"+company.getReviewId(), Company.class );
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
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