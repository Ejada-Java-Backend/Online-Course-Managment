package com.course.management.Services;

import com.course.management.Models.Student;
import com.course.management.Repositories.StudentRepository;
import com.course.management.Exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with username: " + username));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findByEnrollmentsCourseId(courseId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getStudentsWithoutEnrollments() {
        return studentRepository.findByEnrollmentsIsEmpty();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getStudentsWithScoreGreaterThan(Double score) {
        return studentRepository.findStudentsWithScoreValueGreaterThan(score);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getStudentsWithReviewRatingGreaterThan(int minRating) {
        return studentRepository.findStudentsWithReviewRatingGreaterThan(minRating);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getStudentsWithEnrollmentsGreaterThan(int count) {
        return studentRepository.findStudentsWithEnrollmentsGreaterThan(count);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
