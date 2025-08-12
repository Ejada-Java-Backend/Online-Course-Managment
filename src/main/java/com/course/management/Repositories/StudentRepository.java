package com.course.management.Repositories;

import com.course.management.Models.Grade;
import com.course.management.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByEnrollmentsCourseId(Long courseId);

    Optional<Student> findByUsername(String username);

    List<Student> findByEnrollmentsIsEmpty();

    @Query("select distinct s from Student s join s.grades g where g.score > :score")
    List<Student> findStudentsWithScoreValueGreaterThan(@Param("score") Double score);

    @Query("select distinct s from Student s join s.reviews r where r.rating > :minRating")
    List<Student> findStudentsWithReviewRatingGreaterThan(@Param("minRating") int minRating);

    @Query("select s from Student s where size(s.enrollments) > :count")
    List<Student> findStudentsWithEnrollmentsGreaterThan(@Param("count") int count);



}
