package com.app.controller;

import com.app.dto.LoginRequest;
import com.app.entity.Admin;
import com.app.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/admin/auth")
@Validated
public class AuthController {

    private final AdminService service;

    public AuthController(AdminService service) {
        this.service = service;
    }

    // Register: send JSON with emailId & password (Admin fields)
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@Valid @RequestBody Admin admin) {
        Admin saved = service.register(admin);
        return ResponseEntity.ok(saved);
    }

    // Login: send JSON { "emailId": "...", "password": "..." }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest req) {
        service.login(req.getEmailId(), req.getPassword());
        return ResponseEntity.ok("Logged in successfully");
    }
}

