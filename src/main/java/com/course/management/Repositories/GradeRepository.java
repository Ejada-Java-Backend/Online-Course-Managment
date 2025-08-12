package com.course.management.Repositories;

import com.course.management.Models.Grade;
import com.course.management.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);

    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("select avg(g.score) from Grade g where g.student.id = :studentId")
    Double findAverageScoreByStudentId(@Param("studentId") Long studentId);



    @Query("select avg(g.score) from Grade g where g.course.id = :courseId")
    Double findAverageScoreByCourseId(@Param("courseId") Long courseId);

    @Query("select g.student from Grade g where g.course.id = :courseId order by g.score desc")
    List<Student>findTopStudentsByCourseId(@Param("courseId") Long courseId, Pageable pageable);








}
