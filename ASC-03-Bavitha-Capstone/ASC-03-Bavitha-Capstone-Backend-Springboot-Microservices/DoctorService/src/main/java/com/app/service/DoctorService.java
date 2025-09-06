package com.app.service;

import com.app.dto.CreateDoctorRequest;
import com.app.dto.UpdateDoctorRequest;
import com.app.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor create(CreateDoctorRequest req);
    Doctor getById(String id);
    List<Doctor> getAll();
    Doctor update(String id, UpdateDoctorRequest req);
    void delete(String id);


    List<Doctor> search(String id, String name, String specialization, String hospitalId, String email);
}

