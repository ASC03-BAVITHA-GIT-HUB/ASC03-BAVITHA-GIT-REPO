package com.app.service;

import com.app.dto.CreatePatientRequest;
import com.app.dto.UpdatePatientRequest;
import com.app.entity.Patient;
import com.app.exception.InvalidDoctorIdException;
import com.app.exception.NotFoundException;
import com.app.repository.PatientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public PatientServiceImpl(PatientRepository repo) {
        this.repo = repo;
    }

    private String nextPatientId() {
        String prefix = "P";
        String last = repo.findTopByIdStartingWithOrderByIdDesc(prefix)
                .map(Patient::getId)
                .orElse(prefix + "0000");
        int next = Integer.parseInt(last.substring(1)) + 1;
        return String.format("%s%04d", prefix, next);
    }


    private boolean doctorExists(String doctorId) {
        String sql = "SELECT COUNT(1) FROM Doctor WHERE DoctorId = :id";
        Query q = entityManager.createNativeQuery(sql).setParameter("id", doctorId);
        Number n = (Number) q.getSingleResult();
        return n != null && n.longValue() > 0;
    }

    @Override
    @Transactional
    public Patient create(CreatePatientRequest req) {
        final String email = req.getEmail().trim().toLowerCase();
        final String doctorId = req.getDoctorId().trim();

        if (!doctorExists(doctorId)) {
            throw new InvalidDoctorIdException("Invalid doctor id: " + doctorId);
        }
        if (repo.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Patient email already in use");
        }

        String id = nextPatientId();
        Patient p = new Patient(
                id,
                req.getName().trim(),
                email,
                req.getPhone(),
                req.getGender(),
                req.getAddress(),
                req.getCity(),
                req.getBloodGroup(),
                doctorId
        );

        try {
            return repo.save(p);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Duplicate data: " + ex.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public Patient getById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Patient not found: " + id));
    }

    @Override
    public List<Patient> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public Patient update(String id, UpdatePatientRequest req) {
        Patient p = getById(id);

        final String email = req.getEmail().trim().toLowerCase();
        final String doctorId = req.getDoctorId().trim();

        if (!doctorExists(doctorId)) {
            throw new InvalidDoctorIdException("Invalid doctor id: " + doctorId);
        }
        if (repo.existsByEmailIgnoreCaseAndIdNot(email, id)) {
            throw new IllegalArgumentException("Patient email already in use");
        }

        p.setName(req.getName().trim());
        p.setEmail(email);
        p.setPhone(req.getPhone());
        p.setGender(req.getGender());
        p.setAddress(req.getAddress());
        p.setCity(req.getCity());
        p.setBloodGroup(req.getBloodGroup());
        p.setDoctorId(doctorId);

        try {
            return repo.save(p);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Duplicate data: " + ex.getMostSpecificCause().getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repo.existsById(id)) throw new NotFoundException("Patient not found: " + id);
        repo.deleteById(id);
    }

    @Override
    public List<Patient> search(String id, String name, String city, String bloodGroup, String email) {
        if (id != null && !id.isBlank()) return Collections.singletonList(getById(id));
        if (name != null && !name.isBlank()) return repo.findByNameContainingIgnoreCase(name);
        if (city != null && !city.isBlank()) return repo.findByCityContainingIgnoreCase(city);
        if (bloodGroup != null && !bloodGroup.isBlank()) return repo.findByBloodGroupIgnoreCase(bloodGroup);
        if (email != null && !email.isBlank()) return repo.findByEmailContainingIgnoreCase(email);
        return repo.findAll();
    }
}
