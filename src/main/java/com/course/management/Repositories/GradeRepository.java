package com.course.management.Repositories;

import com.course.management.DTO.StudentGradeDTO;
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

    @Query("select new com.course.management.DTO.StudentGradeDTO(g.student.username,g.score) from Grade g where g.course.id = :courseId order by g.score desc")
    List<StudentGradeDTO>findTopStudentsByCourseId(@Param("courseId") Long courseId, Pageable pageable);


    @Query("SELECT COUNT(DISTINCT g.student.id) FROM Grade g WHERE g.course.id = :courseId")
    Long countDistinctStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT g.score FROM Grade g WHERE g.course.id = :courseId ORDER BY g.score ASC")
    List<Double> findScoresByCourseIdOrderByScore(@Param("courseId") Long courseId);





}
