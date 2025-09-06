package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EmailId", nullable = false, unique = true, length = 320)
    @NotBlank(message = "EmailId cannot be blank")
    private String emailId;
    @Column(name = "Password", nullable = false, length = 100)
    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
            message = "Password must contain at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and be at least 8 characters long"
    )
    private String password;


    @Column(name = "FailedAttempts", nullable = false)
    private int failedAttempts = 0;

    @Column(name = "LockUntil")
    private Instant lockUntil;

    public Admin() {

    }

    public Admin(Long id, String emailId, String password) {
        this.id = id;
        this.emailId = emailId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }

    public Instant getLockUntil() { return lockUntil; }
    public void setLockUntil(Instant lockUntil) { this.lockUntil = lockUntil; }
}


