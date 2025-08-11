package com.course.management.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("STUDENT")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "students")
public class Student extends User{

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments=new ArrayList<>();
    @OneToMany(mappedBy = "student")
    private List<Review> reviews=new ArrayList<>();
    @OneToMany(mappedBy = "student")
    private List<Grade> grades=new ArrayList<>();
}
