// src/main/java/com/app/controller/AdminController.java
package com.app.controller;

import com.app.dto.AdminDto;
import com.app.entity.Admin;
import com.app.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    // READ all (hide password via DTO)
    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> list = service.getAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // READ by id (hide password via DTO)
    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {
        Admin a = service.getById(id);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(a));
    }

    // DELETE by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Admin with ID " + id + " deleted successfully");
    }


    // --- mapper: Admin -> AdminDto (no password) ---
    private AdminDto toDto(Admin a) {
        return new AdminDto(a.getId(), a.getEmailId());
    }
}
