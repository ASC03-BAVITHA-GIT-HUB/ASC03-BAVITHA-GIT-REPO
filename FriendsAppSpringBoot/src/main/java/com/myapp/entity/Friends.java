package com.myapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL_ID", nullable = false)
    private String emailId;
    @Column(name = "PHONE", nullable = false)
    private String Phone;
    @Column(name = "GENDER", nullable = false)
    private String Gender;

    public Friends(Long id, String name, String emailId, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        Phone = phone;
        Gender = gender;
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
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}

