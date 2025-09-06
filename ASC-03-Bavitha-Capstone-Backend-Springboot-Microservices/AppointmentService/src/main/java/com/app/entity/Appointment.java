package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {


    @Id
    @Column(name = "AppointmentId", length = 5, nullable = false, updatable = false, unique = true)
    private String id;


    @Column(name = "PatientId", length = 5, nullable = false)
    @NotBlank(message = "PatientId is required")
    private String patientId;


    @Column(name = "DoctorId", length = 5)
    private String doctorId;

    @Column(name = "WhenAt", nullable = false)
    @NotNull(message = "Appointment date/time is required")
    @FutureOrPresent(message = "Appointment time cannot be in the past")
    private LocalDateTime whenAt;

    @Column(name = "Reason", length = 255)
    private String reason;

    @Column(name = "Status", length = 30, nullable = false)
    @NotBlank(message = "Status is required")
    private String status;

    protected Appointment() {}

    public Appointment(String id, String patientId, String doctorId,
                       LocalDateTime whenAt, String reason, String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.whenAt = whenAt;
        this.reason = reason;
        this.status = status;
    }

    public void setId(String id) {
        if (this.id != null) throw new IllegalStateException("ID cannot be modified");
        this.id = id;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDateTime getWhenAt() { return whenAt; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public void setWhenAt(LocalDateTime whenAt) { this.whenAt = whenAt; }
    public void setReason(String reason) { this.reason = reason; }
    public void setStatus(String status) { this.status = status; }
}

