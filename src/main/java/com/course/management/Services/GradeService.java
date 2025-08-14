package com.course.management.Services;

import com.course.management.DTO.CourseStatsDTO;
import com.course.management.DTO.StudentGradeDTO;
import com.course.management.Exceptions.CourseNotFoundException;
import com.course.management.Exceptions.IllegalArgumentException;
import com.course.management.Models.Course;
import com.course.management.Models.Student;
import com.course.management.Repositories.CourseRepository;
import com.course.management.Repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository,CourseRepository courseRepository) {

        this.gradeRepository = gradeRepository;
        this.courseRepository=courseRepository;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentGradeDTO> getTopNStudentsByCourse(Long courseId, int limit) {

        Optional<Course> course=courseRepository.findById(courseId);
        if(!course.isPresent())
        {
            throw new CourseNotFoundException("this course is not found ");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero");
        }

        return gradeRepository.findTopStudentsByCourseId(courseId, PageRequest.of(0, limit));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CourseStatsDTO getCourseStats(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));

        Long studentCount = gradeRepository.countDistinctStudentsByCourseId(courseId);
        Double mean = gradeRepository.findAverageScoreByCourseId(courseId);
        if (mean == null) mean = 0.0;

        List<Double> scores = gradeRepository.findScoresByCourseIdOrderByScore(courseId);

        double median = 0.0;
        if (!scores.isEmpty()) {
            int size = scores.size();
            if (size % 2 == 0) {
                median = (scores.get(size / 2 - 1) + scores.get(size / 2)) / 2;
            } else {
                median = scores.get(size / 2);
            }
        }

        return new CourseStatsDTO(course.getId(), course.getTitle(), studentCount, mean, median);
    }

}