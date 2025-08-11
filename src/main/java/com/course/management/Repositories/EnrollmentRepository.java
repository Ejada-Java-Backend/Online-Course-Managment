package com.course.management.Repositories;

import com.course.management.Models.Enrollment;
import com.course.management.Models.SemesterTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);

    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Enrollment> findByTerm(SemesterTerm term);

    List<Enrollment> findByStudentIdAndTerm(Long studentId, SemesterTerm term);

    @Query("select count(e) from Enrollment e where e.student.id = :studentId")
    long countEnrollmentsByStudentId(@Param("studentId") Long studentId);

    @Query("select count(distinct e.student.id) from Enrollment e where e.course.id = :courseId")
    long countDistinctStudentsEnrolledInCourse(@Param("courseId") Long courseId);
}
