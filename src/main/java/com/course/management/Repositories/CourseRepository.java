package com.course.management.Repositories;

import com.course.management.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByCategoryName(String categoryName);

    @Query("select c from Course c where c.publisher.username = :username")
    List<Course> findByPublisherUsername(@Param("username") String username);

    List<Course> findByReleaseDateAfter(LocalDate date);

    List<Course> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("select c from Course c where size(c.enrollments) > :minStudents")
    List<Course> findByMinEnrollments(@Param("minStudents") int minStudents);


}
