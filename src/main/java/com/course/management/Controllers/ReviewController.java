package com.course.management.Controllers;

import com.course.management.Models.Review;
import com.course.management.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Review>> getReviewsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(reviewService.getReviewsByCourseId(courseId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Review>> getReviewsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(reviewService.getReviewsByStudentId(studentId));
    }

    @GetMapping("/rating/greater-than/{rating}")
    public ResponseEntity<List<Review>> getReviewsWithRatingGreaterThan(@PathVariable int rating) {
        return ResponseEntity.ok(reviewService.getReviewsWithRatingGreaterThan(rating));
    }

    @GetMapping("/rating/exact/{rating}")
    public ResponseEntity<List<Review>> getReviewsByExactRating(@PathVariable int rating) {
        return ResponseEntity.ok(reviewService.getReviewsByExactRating(rating));
    }

    @GetMapping("/course/{courseId}/latest")
    public ResponseEntity<List<Review>> getReviewsByCourseIdOrderedDesc(@PathVariable Long courseId) {
        return ResponseEntity.ok(reviewService.getReviewsByCourseIdOrderedDesc(courseId));
    }

    @GetMapping("/student/{studentId}/oldest")
    public ResponseEntity<List<Review>> getReviewsByStudentIdOrderedAsc(@PathVariable Long studentId) {
        return ResponseEntity.ok(reviewService.getReviewsByStudentIdOrderedAsc(studentId));
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        Review existingReview = reviewService.getReviewById(id);
        existingReview.setCourse(updatedReview.getCourse());
        existingReview.setStudent(updatedReview.getStudent());
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setCreatedAt(updatedReview.getCreatedAt());
        Review savedReview = reviewService.saveReview(existingReview);
        return ResponseEntity.ok(savedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
