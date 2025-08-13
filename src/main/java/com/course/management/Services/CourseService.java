package com.course.management.Services;

import com.course.management.Exceptions.CoursesNotFoundException;
import com.course.management.Exceptions.IllegalArgumentException;
import com.course.management.Models.Course;
import com.course.management.Repositories.CourseRepository;
import com.course.management.Exceptions.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + id));
    }

    public List<Course> getCoursesByCategoryName(String categoryName) {
        return courseRepository.findByCategoryName(categoryName);
    }

    public List<Course> getCoursesByPublisherUsername(String username) {
        return courseRepository.findByPublisherUsername(username);
    }

    public List<Course> getCoursesReleasedAfter(LocalDate date) {
        return courseRepository.findByReleaseDateAfter(date);
    }

    public List<Course> getCoursesReleasedBetween(LocalDate startDate, LocalDate endDate) {
        return courseRepository.findByReleaseDateBetween(startDate, endDate);
    }

    public List<Course> getCoursesWithMinEnrollments(int minStudents) {
        return courseRepository.findByMinEnrollments(minStudents);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course not found with ID: " + id);
        }
        courseRepository.deleteById(id);
    }

    public List<Course> getCoursesByMark(double mark)
    {
        if(mark<=0)
            throw new IllegalArgumentException("the mark must be greater than 0 ");

        return courseRepository.findByMark(mark);
    }
    public List<Course> getCoursesGreaterThanMark(double mark)
    {
       return courseRepository.findByMarkGreaterThan(mark);
    }

    public List<Course> getCoursesSmallerThanMark(double mark)
    {
        if(mark<=0)
        throw new IllegalArgumentException("the mark must be greater than 0 ");

        return courseRepository.findByMarkLessThan(mark);


    }

    public List<Course> getCoursesBetweenMarks(double minMark,double maxMark)
    {
        if(minMark<=0)
            throw new IllegalArgumentException("the min mark must be greater than 0 ");

        if (maxMark <= minMark)
            throw new IllegalArgumentException("The max mark must be greater than the min mark");


        return courseRepository.findByMarkBetween(minMark,maxMark);



    }

}
