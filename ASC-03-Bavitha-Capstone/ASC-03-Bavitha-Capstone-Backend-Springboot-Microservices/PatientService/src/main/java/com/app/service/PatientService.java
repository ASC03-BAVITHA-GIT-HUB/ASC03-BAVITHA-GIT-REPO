package com.app.service;

import com.app.dto.CreatePatientRequest;
import com.app.dto.UpdatePatientRequest;
import com.app.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient create(CreatePatientRequest req);
    Patient getById(String id);
    List<Patient> getAll();
    Patient update(String id, UpdatePatientRequest req);
    void delete(String id);
    List<Patient> search(String id, String name, String city, String bloodGroup, String email);
}
