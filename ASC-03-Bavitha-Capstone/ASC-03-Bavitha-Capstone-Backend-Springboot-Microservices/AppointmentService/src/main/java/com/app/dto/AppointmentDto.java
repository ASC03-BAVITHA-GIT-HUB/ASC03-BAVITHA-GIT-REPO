package com.app.dto;

import java.time.LocalDateTime;

public class AppointmentDto {
    private final String id;
    private final String patientId;
    private final String doctorId;
    private final LocalDateTime whenAt;
    private final String reason;
    private final String status;

    public AppointmentDto(String id, String patientId, String doctorId,
                          LocalDateTime whenAt, String reason, String status) {
        this.id = id; this.patientId = patientId; this.doctorId = doctorId;
        this.whenAt = whenAt; this.reason = reason; this.status = status;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDateTime getWhenAt() { return whenAt; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
}

