package com.course.management.Repositories;

import com.course.management.Models.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"courses"})
    Optional<Admin> findById(Long id);

    @Query("select distinct a FROM Admin a join a.courses c where c.category.id = :categoryId")
    List<Admin> findAdminsByCourseCategory(@Param("categoryId") Long categoryId);
}
