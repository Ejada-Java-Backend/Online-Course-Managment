package com.course.management.Repositories;

import com.course.management.Models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Long> {

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);

    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("select avg(g.score) from Grade g where g.student.id = :studentId")
    Double findAverageScoreByStudentId(@Param("studentId") Long studentId);

    @Query("select avg(g.score) from Grade g where g.course.id = :courseId")
    Double findAverageScoreByCourseId(@Param("courseId") Long courseId);

}
