package com.course.management.Services;

import com.course.management.Models.Review;
import com.course.management.Repositories.ReviewRepository;
import com.course.management.Exceptions.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with ID: " + id));
    }

    public List<Review> getReviewsByCourseId(Long courseId) {
        return reviewRepository.findByCourseId(courseId);
    }

    public List<Review> getReviewsByStudentId(Long studentId) {
        return reviewRepository.findByStudentId(studentId);
    }

    public List<Review> getReviewsWithRatingGreaterThan(int rating) {
        return reviewRepository.findByRatingGreaterThan(rating);
    }

    public List<Review> getReviewsByExactRating(int rating) {
        return reviewRepository.findByRating(rating);
    }

    public List<Review> getReviewsByCourseIdOrderedDesc(Long courseId) {
        return reviewRepository.findByCourseIdOrderByCreatedAtDesc(courseId);
    }

    public List<Review> getReviewsByStudentIdOrderedAsc(Long studentId) {
        return reviewRepository.findByStudentIdOrderByCreatedAtAsc(studentId);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review not found with ID: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
