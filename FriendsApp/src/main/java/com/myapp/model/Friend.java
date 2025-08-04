package com.myapp.model;

import java.sql.Date;

public class Friend {
    private String id;
    private String Name;
    private String Email;
    private String Phone;
    private String Gender;
    private int Age;
    private Date Dob;
    private String City;


    // constructor
    public Friend(String id, String name, String email, String phone, String gender, int age, Date dob, String city) {
        this.id = id;
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Gender = gender;
        this.Age = age;
        this.Dob = dob;
        this.City = city;
    }

    //getters

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }

    public Date getDob() {
        return Dob;
    }

    public String getCity() {
        return City;
    }

    //setters

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public void setCity(String city) {
        City = city;
    }
}
