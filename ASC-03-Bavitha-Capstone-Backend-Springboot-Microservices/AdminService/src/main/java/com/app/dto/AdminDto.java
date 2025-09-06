package com.app.dto;

public class AdminDto {
    private Long id;
    private String emailId;
    private boolean enabled; // optional: if you add this later

    public AdminDto() {}

    public AdminDto(Long id, String emailId) {
        this.id = id;
        this.emailId = emailId;
    }

    public AdminDto(Long id, String emailId, boolean enabled) {
        this.id = id;
        this.emailId = emailId;
        this.enabled = enabled;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
