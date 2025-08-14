package com.course.management.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("STUDENT")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter @Getter
@Table(name = "students")
public class Student extends User{

    @OneToMany(mappedBy = "student")
    @JsonManagedReference("student-enrollments")
    private List<Enrollment> enrollments=new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonManagedReference("student-reviews")
    private List<Review> reviews=new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonManagedReference("student-grades")
    private List<Grade> grades=new ArrayList<>();
}
