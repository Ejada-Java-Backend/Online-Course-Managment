package com.course.management.DTO;

import com.course.management.Models.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradeDTO {
    private String studentname;
    private double score;
}
