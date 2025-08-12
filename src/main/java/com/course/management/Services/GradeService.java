package com.course.management.Services;

import com.course.management.Models.Student;
import com.course.management.Repositories.CourseRepository;
import com.course.management.Repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {

        this.gradeRepository = gradeRepository;
    }

    public List<Student> getTopNStudentsByCourse(Long courseId, int limit) {

        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero");
        }

        return gradeRepository.findTopStudentsByCourseId(courseId, PageRequest.of(0, limit));
    }
}