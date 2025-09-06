package com.app.service;

import com.app.dto.CreateHospitalRequest;
import com.app.entity.Hospital;
import com.app.exception.NotFoundException;
import com.app.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository repo;

    public HospitalServiceImpl(HospitalRepository repo) {
        this.repo = repo;
    }


    private String nextHospitalId() {
        String prefix = "H";
        String last = repo.findTopByIdStartingWithOrderByIdDesc(prefix)
                .map(Hospital::getId)
                .orElse(prefix + "0000");
        int nextNum = Integer.parseInt(last.substring(1)) + 1;
        return String.format("%s%04d", prefix, nextNum);
    }

    @Override
    @Transactional
    public Hospital create(CreateHospitalRequest req) {
        if (repo.existsByNameIgnoreCase(req.getName())) {
            throw new IllegalArgumentException("Hospital with the same name already exists");
        }
        if (req.getEmail() != null && repo.existsByEmailIgnoreCase(req.getEmail().trim().toLowerCase())) {
            throw new IllegalArgumentException("Hospital email already in use");
        }

        String id = nextHospitalId();
        Hospital h = new Hospital(
                null,
                req.getName().trim(),
                req.getEmail() == null ? null : req.getEmail().trim().toLowerCase(),
                req.getPhone(),
                req.getAddress(),
                req.getCity()
        );
        h.setId(id);
        return repo.save(h);
    }

    @Override
    public Hospital getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital not found: " + id));
    }

    @Override
    public List<Hospital> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Hospital not found: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public List<Hospital> search(String id, String name, String city) {
        if (id != null && !id.trim().isEmpty()) {
            return List.of(getById(id));
        }
        if (name != null && !name.trim().isEmpty()) {
            return repo.findByNameContainingIgnoreCase(name);
        }
        if (city != null && !city.trim().isEmpty()) {
            return repo.findByCityContainingIgnoreCase(city);
        }
        return repo.findAll();
    }
}
