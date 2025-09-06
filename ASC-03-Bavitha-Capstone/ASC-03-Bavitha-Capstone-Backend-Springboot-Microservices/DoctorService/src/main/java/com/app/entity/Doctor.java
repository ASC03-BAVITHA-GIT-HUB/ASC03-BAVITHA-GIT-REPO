package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Doctor")
public class Doctor {


    @Id
    @Column(name = "DoctorId", length = 5, nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "Name", nullable = false, length = 100)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "Email", nullable = false, length = 320, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Specialization", nullable = false, length = 100)
    @NotBlank(message = "Specialization is required")
    private String specialization;

    @Column(name = "HospitalId", nullable = false, length = 5)
    private String hospitalId;

    protected Doctor() {}

    public Doctor(String id, String name, String email, String phone, String specialization, String hospitalId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.hospitalId = hospitalId;
    }

    public void setId(String id) {
        if (this.id != null) throw new IllegalStateException("ID cannot be modified");
        this.id = id;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getSpecialization() { return specialization; }
    public String getHospitalId() { return hospitalId; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }
}
