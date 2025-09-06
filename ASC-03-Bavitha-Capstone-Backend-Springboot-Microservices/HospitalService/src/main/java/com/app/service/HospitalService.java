package com.app.service;


import com.app.dto.CreateHospitalRequest;
import com.app.entity.Hospital;

import java.util.List;

public interface HospitalService {
    Hospital create(CreateHospitalRequest req);
    Hospital getById(String id);
    List<Hospital> getAll();
    void delete(String id);
    List<Hospital> search(String id, String name, String city);
}

