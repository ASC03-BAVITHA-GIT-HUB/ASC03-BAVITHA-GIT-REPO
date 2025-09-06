package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @Column(name = "PatientId", length = 5, nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "Name", nullable = false, length = 120)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "Email", nullable = false, length = 320, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Gender", length = 20)
    private String gender;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "City", length = 80)
    private String city;

    @Column(name = "BloodGroup", length = 10)
    private String bloodGroup;

    @Column(name = "DoctorId", length = 5, nullable = false)
    private String doctorId;

    protected Patient() {}

    public Patient(String id, String name, String email, String phone,
                   String gender, String address, String city, String bloodGroup,
                   String doctorId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.bloodGroup = bloodGroup;
        this.doctorId = doctorId;
    }

    public void setId(String id) {
        if (this.id != null) throw new IllegalStateException("ID cannot be modified");
        this.id = id;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getBloodGroup() { return bloodGroup; }
    public String getDoctorId() { return doctorId; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
}
