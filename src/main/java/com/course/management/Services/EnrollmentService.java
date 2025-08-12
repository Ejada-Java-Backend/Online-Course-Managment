package com.course.management.Services;

import com.course.management.Models.Enrollment;
import com.course.management.Models.EnrollmentStatus;
import com.course.management.Models.SemesterTerm;
import com.course.management.Models.Student;
import com.course.management.Repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found with ID: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public Enrollment getEnrollmentByStudentAndCourse(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment == null) {
            throw new RuntimeException("Enrollment not found for student ID: " + studentId + " and course ID: " + courseId);
        }
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByTerm(SemesterTerm term) {
        return enrollmentRepository.findByTerm(term);
    }

    public List<Enrollment> getEnrollmentsByStudentAndTerm(Long studentId, SemesterTerm term) {
        return enrollmentRepository.findByStudentIdAndTerm(studentId, term);
    }

    public long countEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.countEnrollmentsByStudentId(studentId);
    }

    public long countDistinctStudentsInCourse(Long courseId) {
        return enrollmentRepository.countDistinctStudentsEnrolledInCourse(courseId);
    }

 public Enrollment updateStatus(Long enrollmentId, EnrollmentStatus status) {
     Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
             .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + enrollmentId));
     enrollment.setStatus(status);
     return enrollmentRepository.save(enrollment);
 }
}
