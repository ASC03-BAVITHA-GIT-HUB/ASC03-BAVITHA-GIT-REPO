package com.app.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "EmailId cannot be blank")
    private String emailId;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

