package com.course.management.DTO;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long studentId;
    private Long courseId;
    private double score;
}