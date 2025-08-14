package com.course.management.Controller;

import com.course.management.DTO.CourseStatsDTO;
import com.course.management.DTO.StudentGradeDTO;
import com.course.management.Models.Grade;
import com.course.management.Models.Student;
import com.course.management.Repositories.GradeRepository;
import com.course.management.Services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService gradeService;
    @Autowired
    public GradeController(GradeService gradeService)
    {
        this.gradeService=gradeService;
    }

    @GetMapping("/{courseId}/top-students")
    public List<StudentGradeDTO> getTopStudents(@PathVariable Long courseId, @RequestParam int limit) {
        return gradeService.getTopNStudentsByCourse(courseId, limit);
    }

    @GetMapping("/{courseId}/stats")
    public CourseStatsDTO getCourseStats(@PathVariable Long courseId) {
        return gradeService.getCourseStats(courseId);
    }
    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        return gradeService.getGradesByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Grade> getGradesByCourse(@PathVariable Long courseId) {
        return gradeService.getGradesByCourse(courseId);
    }
    @GetMapping("/student/{studentId}/course/{courseId}")
    public Grade getGradeByStudentAndCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return gradeService.getGradeByStudentAndCourse(studentId, courseId);
    }

}
