package com.course.management.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "admins")
public class Admin extends User{

    @OneToMany(mappedBy = "publisher")
    private List<Course> courses=new ArrayList<>();
}
