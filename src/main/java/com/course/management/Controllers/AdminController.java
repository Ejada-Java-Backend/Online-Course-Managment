package com.course.management.Controllers;

import com.course.management.Models.Admin;
import com.course.management.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username) {
        Admin admin = adminService.getAdminByUsername(username);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
        Admin admin = adminService.getAdminByEmail(email);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Admin>> getAdminsByCourseCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(adminService.getAdminsByCourseCategory(categoryId));
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(adminService.existsByUsername(username));
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.existsByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        Admin existingAdmin = adminService.getAdminById(id);
        existingAdmin.setUsername(updatedAdmin.getUsername());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        // Add any other fields you want to update here

        Admin savedAdmin = adminService.saveAdmin(existingAdmin);
        return ResponseEntity.ok(savedAdmin);
    }
}
