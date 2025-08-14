package com.course.management.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference("student-grades")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference("course-grades")
    private Course course;

    @Column(nullable = false)
    private double score;

}
