package com.course.management.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder @Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private double mark;
    @ManyToOne
    @JoinColumn(name = "category_Id",nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "publisher_Id",nullable = false)
    private Admin publisher;
    @OneToMany(mappedBy = "course")
    private List<Enrollment>enrollments=new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Review>reviews=new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Grade>grades=new ArrayList<>();

//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", release_date=" + releaseDate +
//                ", category=" + (category != null ? category.getName() : "null") +
//                ", publisher=" + (publisher != null ? publisher.getUsername() : "null") +
//                '}';
//    }
}
