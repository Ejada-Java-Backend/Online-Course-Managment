package com.course.management.Models;

import com.course.management.Enum.EnrollmentStatusEnum;
import com.course.management.Enum.SemesterTermEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference("student-enrollments")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference("course-enrollments")
    private Course course;

    @Enumerated(EnumType.STRING)
    private SemesterTermEnum term;

    @Column(nullable = false)
    private LocalDate enrolledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatusEnum status;

}
