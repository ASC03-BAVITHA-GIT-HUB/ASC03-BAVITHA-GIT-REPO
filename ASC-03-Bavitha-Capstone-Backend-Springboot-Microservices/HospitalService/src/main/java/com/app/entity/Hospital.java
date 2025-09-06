package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Hospital")
public class Hospital {


    @Id
    @Column(name = "HospitalId", length = 5, nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "Name", nullable = false, unique = true, length = 150)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "Email", length = 320, unique = true)
    @Email(message = "Email must be valid")
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "City", length = 80)
    @Size(max = 80, message = "City must be at most 80 characters")
    private String city;

    protected Hospital() {}

    public Hospital(String id, String name, String email, String phone, String address, String city) {
        this.id = id; // set once
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }


    public void setId(String id) {
        if (this.id != null) throw new IllegalStateException("ID cannot be modified");
        this.id = id;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getCity() { return city; }


    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
}

