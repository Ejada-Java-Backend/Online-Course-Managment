package com.course.management.Services;

import com.course.management.Models.Admin;
import com.course.management.Repositories.AdminRepository;
import com.course.management.Exceptions.AdminNotFoundException;
import com.course.management.Exceptions.DuplicateUsernameException;
import com.course.management.Exceptions.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + id));
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with username: " + username));
    }

    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with email: " + email));
    }

    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    public List<Admin> getAdminsByCourseCategory(Long categoryId) {
        return adminRepository.findAdminsByCourseCategory(categoryId);
    }

    public Admin saveAdmin(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new DuplicateUsernameException("Username already exists: " + admin.getUsername());
        }
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + admin.getEmail());
        }
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
        adminRepository.deleteById(id);
    }
}
