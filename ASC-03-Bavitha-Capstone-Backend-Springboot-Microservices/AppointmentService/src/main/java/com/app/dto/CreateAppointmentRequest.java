package com.app.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateAppointmentRequest {
    @NotBlank(message = "PatientId is required")
    private String patientId;
    private String doctorId;

    @NotNull(message = "Appointment date/time is required")
    @FutureOrPresent(message = "Appointment time cannot be in the past")
    private LocalDateTime whenAt;

    private String reason;

    @NotBlank(message = "Status is required")
    private String status; // SCHEDULED, COMPLETED, CANCELED

    public CreateAppointmentRequest() {}

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public LocalDateTime getWhenAt() { return whenAt; }
    public void setWhenAt(LocalDateTime whenAt) { this.whenAt = whenAt; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

