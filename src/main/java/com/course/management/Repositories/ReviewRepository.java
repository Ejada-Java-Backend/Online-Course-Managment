package com.course.management.Repositories;

import com.course.management.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByCourseId(Long courseId);

    List<Review> findByStudentId(Long studentId);

    List<Review> findByRatingGreaterThan(int rating);

    List<Review> findByRating(int rating);

    List<Review> findByCourseIdOrderByCreatedAtDesc(Long courseId);

    List<Review> findByStudentIdOrderByCreatedAtAsc(Long studentId);

}
