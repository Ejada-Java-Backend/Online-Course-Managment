package com.course.management.Controllers;

import com.course.management.Models.Enrollment;
import com.course.management.Models.EnrollmentStatus;
import com.course.management.Models.SemesterTerm;
import com.course.management.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollment);
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.saveEnrollment(enrollment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment updatedEnrollment) {
        Enrollment existing = enrollmentService.getEnrollmentById(id);
        existing.setStudent(updatedEnrollment.getStudent());
        existing.setCourse(updatedEnrollment.getCourse());
        existing.setTerm(updatedEnrollment.getTerm());
        existing.setStatus(updatedEnrollment.getStatus());
        Enrollment saved = enrollmentService.saveEnrollment(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Enrollment> getEnrollmentByStudentAndCourse(@PathVariable Long studentId,
                                                                      @PathVariable Long courseId) {
        Enrollment enrollment = enrollmentService.getEnrollmentByStudentAndCourse(studentId, courseId);
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping("/term")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByTerm(@RequestParam SemesterTerm term) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByTerm(term);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}/term")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentAndTerm(@PathVariable Long studentId,
                                                                           @RequestParam SemesterTerm term) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentAndTerm(studentId, term);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}/count")
    public ResponseEntity<Long> countEnrollmentsByStudent(@PathVariable Long studentId) {
        long count = enrollmentService.countEnrollmentsByStudent(studentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/course/{courseId}/students/count")
    public ResponseEntity<Long> countDistinctStudentsInCourse(@PathVariable Long courseId) {
        long count = enrollmentService.countDistinctStudentsInCourse(courseId);
        return ResponseEntity.ok(count);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Enrollment> updateStatus(@PathVariable Long id, @RequestParam EnrollmentStatus status) {
        Enrollment updated = enrollmentService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
