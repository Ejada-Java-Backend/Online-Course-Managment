package com.course.management.Services;

import com.course.management.Models.Enrollment;
import com.course.management.Enum.EnrollmentStatusEnum;
import com.course.management.Enum.SemesterTermEnum;
import com.course.management.Repositories.EnrollmentRepository;
import com.course.management.Exceptions.EnrollmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with ID: " + id));
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException("Enrollment not found with ID: " + id);
        }
        enrollmentRepository.deleteById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Enrollment getEnrollmentByStudentAndCourse(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment == null) {
            throw new EnrollmentNotFoundException("Enrollment not found for student ID: " + studentId + " and course ID: " + courseId);
        }
        return enrollment;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Enrollment> getEnrollmentsByTerm(SemesterTermEnum term) {
        return enrollmentRepository.findByTerm(term);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Enrollment> getEnrollmentsByStudentAndTerm(Long studentId, SemesterTermEnum term) {
        return enrollmentRepository.findByStudentIdAndTerm(studentId, term);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public long countEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.countEnrollmentsByStudentId(studentId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public long countDistinctStudentsInCourse(Long courseId) {
        return enrollmentRepository.countDistinctStudentsEnrolledInCourse(courseId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Enrollment updateStatus(Long enrollmentId, EnrollmentStatusEnum status) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with ID: " + enrollmentId));
        enrollment.setStatus(status);
        return enrollmentRepository.save(enrollment);
    }
}
