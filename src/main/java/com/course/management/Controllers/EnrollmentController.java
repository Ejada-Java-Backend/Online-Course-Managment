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
        try {
            return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.saveEnrollment(enrollment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment updatedEnrollment) {
        try {
            Enrollment existing = enrollmentService.getEnrollmentById(id);
            existing.setStudent(updatedEnrollment.getStudent());
            existing.setCourse(updatedEnrollment.getCourse());
            existing.setTerm(updatedEnrollment.getTerm());
            existing.setStatus(updatedEnrollment.getStatus());
            Enrollment saved = enrollmentService.saveEnrollment(existing);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Enrollment> getEnrollmentByStudentAndCourse(@PathVariable Long studentId,
                                                                      @PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(enrollmentService.getEnrollmentByStudentAndCourse(studentId, courseId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/term")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByTerm(@RequestParam SemesterTerm term) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByTerm(term));
    }

    @GetMapping("/student/{studentId}/term")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentAndTerm(@PathVariable Long studentId,
                                                                           @RequestParam SemesterTerm term) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentAndTerm(studentId, term));
    }

    @GetMapping("/student/{studentId}/count")
    public ResponseEntity<Long> countEnrollmentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.countEnrollmentsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}/students/count")
    public ResponseEntity<Long> countDistinctStudentsInCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.countDistinctStudentsInCourse(courseId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam EnrollmentStatus status) {
        try {
            Enrollment updated = enrollmentService.updateStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
