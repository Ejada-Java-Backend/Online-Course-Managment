package com.course.management.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseStatsDTO {
    private Long courseId;
    private String courseName;
    private Long studentCount;
    private Double meanGrade;
    private Double medianGrade;
}
