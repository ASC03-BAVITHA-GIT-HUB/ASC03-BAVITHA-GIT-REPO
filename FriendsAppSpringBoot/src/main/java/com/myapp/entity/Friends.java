package com.myapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "emailId", nullable = false)
    private String emailId;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "gender", nullable = false)
    private String gender;

    public Friends(){

    }

    public Friends(Long id, String name, String emailId, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}