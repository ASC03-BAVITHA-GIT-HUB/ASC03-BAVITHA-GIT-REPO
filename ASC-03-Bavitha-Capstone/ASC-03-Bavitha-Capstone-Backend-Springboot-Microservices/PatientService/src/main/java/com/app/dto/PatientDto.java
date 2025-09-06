package com.app.dto;

public class PatientDto {
    private final String id;
    private final String name;
    private final String email;
    private final String phone;
    private final String gender;
    private final String address;
    private final String city;
    private final String bloodGroup;
    private final String doctorId;

    public PatientDto(String id, String name, String email, String phone,
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getDoctorId() {
        return doctorId;
    }
}
