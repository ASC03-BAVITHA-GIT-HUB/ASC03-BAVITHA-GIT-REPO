package com.app.dto;

public class LoginResponse {
    private Long id;
    private String emailId;
    private String message;

    public LoginResponse() {}

    public LoginResponse(Long id, String emailId, String message) {
        this.id = id;
        this.emailId = emailId;
        this.message = message;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
