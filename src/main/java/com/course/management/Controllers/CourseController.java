package com.course.management.Controllers;

import com.course.management.Models.Course;
import com.course.management.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Course>> getCoursesByCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(courseService.getCoursesByCategoryName(categoryName));
    }

    @GetMapping("/publisher/{username}")
    public ResponseEntity<List<Course>> getCoursesByPublisherUsername(@PathVariable String username) {
        return ResponseEntity.ok(courseService.getCoursesByPublisherUsername(username));
    }

    @GetMapping("/released-after")
    public ResponseEntity<List<Course>> getCoursesReleasedAfter(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(courseService.getCoursesReleasedAfter(date));
    }

    @GetMapping("/released-between")
    public ResponseEntity<List<Course>> getCoursesReleasedBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(courseService.getCoursesReleasedBetween(startDate, endDate));
    }

    @GetMapping("/min-enrollments/{minStudents}")
    public ResponseEntity<List<Course>> getCoursesWithMinEnrollments(@PathVariable int minStudents) {
        return ResponseEntity.ok(courseService.getCoursesWithMinEnrollments(minStudents));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Course existingCourse = courseService.getCourseById(id);

        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setPublisher(updatedCourse.getPublisher());
        existingCourse.setReleaseDate(updatedCourse.getReleaseDate());
        existingCourse.setEnrollments(updatedCourse.getEnrollments());

        Course savedCourse = courseService.saveCourse(existingCourse);
        return ResponseEntity.ok(savedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

