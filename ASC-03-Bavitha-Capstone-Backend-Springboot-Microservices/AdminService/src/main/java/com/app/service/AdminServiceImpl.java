package com.app.service;

import com.app.entity.Admin;
import com.app.exception.AdminAlreadyExistsException;
import com.app.exception.InvalidCredentialsException;
import com.app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // configurable (defaults to 3 attempts, 30 minutes if not set in application.properties)
    @Value("${admin.auth.maxFailedAttempts:3}")
    private int maxFailedAttempts;

    @Value("${admin.auth.lockMinutes:30}")
    private int lockMinutes;

    public AdminServiceImpl(AdminRepository repo) {
        this.repo = repo;
    }

    @Override
    public Admin register(Admin admin) {
        if (repo.existsByEmailId(admin.getEmailId())) {
            throw new AdminAlreadyExistsException(
                    "Admin with email " + admin.getEmailId() + " already exists"
            );
        }
        // hash password before saving
        String hashedPassword = encoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);

        // initialize lock fields
        admin.setFailedAttempts(0);
        admin.setLockUntil(null);

        return repo.save(admin);
    }

    @Override
    public Admin login(String emailId, String rawPassword) {
        Admin admin = repo.findByEmailId(emailId)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // 1) Check lock status
        if (isLocked(admin)) {
            throw new InvalidCredentialsException("Account locked. Try again later.");
        }

        // 2) Verify password
        if (!encoder.matches(rawPassword, admin.getPassword())) {
            handleFailedAttempt(admin);
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // 3) Success → reset counters
        admin.setFailedAttempts(0);
        admin.setLockUntil(null);
        return repo.save(admin);
    }

    // ---------------- helpers ----------------
    private boolean isLocked(Admin admin) {
        Instant until = admin.getLockUntil();
        return until != null && Instant.now().isBefore(until);
    }

    private void handleFailedAttempt(Admin admin) {
        int attempts = admin.getFailedAttempts() + 1;
        admin.setFailedAttempts(attempts);

        if (attempts >= maxFailedAttempts) {
            admin.setLockUntil(Instant.now().plus(lockMinutes, ChronoUnit.MINUTES));
            // don’t reset failedAttempts here
        }

        repo.save(admin);
    }

    @Override
    public Admin getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Admin> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }


}
