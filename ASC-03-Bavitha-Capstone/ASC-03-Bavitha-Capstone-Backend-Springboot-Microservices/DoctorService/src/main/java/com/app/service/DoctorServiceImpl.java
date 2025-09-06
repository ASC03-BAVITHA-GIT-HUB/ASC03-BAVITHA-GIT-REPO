package com.app.service;

import com.app.dto.CreateDoctorRequest;
import com.app.dto.UpdateDoctorRequest;
import com.app.entity.Doctor;
import com.app.exception.InvalidHospitalException;
import com.app.exception.NotFoundException;
import com.app.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepo;

    @PersistenceContext
    private EntityManager entityManager; // direct DB access for hospital check

    public DoctorServiceImpl(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    private String nextDoctorId() {
        String prefix = "D";
        String last = doctorRepo.findTopByIdStartingWithOrderByIdDesc(prefix)
                .map(Doctor::getId)
                .orElse(prefix + "0000");
        int next = Integer.parseInt(last.substring(1)) + 1;
        return String.format("%s%04d", prefix, next);
    }

    private boolean hospitalExists(String hospitalId) {
        String sql = "SELECT COUNT(*) FROM Hospital WHERE HospitalId = :id";
        Number n = (Number) entityManager
                .createNativeQuery(sql)
                .setParameter("id", hospitalId)
                .getSingleResult();
        return n != null && n.longValue() > 0;
    }

    @Override
    @Transactional
    public Doctor create(CreateDoctorRequest req) {
        if (!hospitalExists(req.getHospitalId().trim())) {
            throw new InvalidHospitalException("Invalid hospital id: " + req.getHospitalId());
        }

        String id = nextDoctorId();
        Doctor doctor = new Doctor(
                id,
                req.getName().trim(),
                req.getEmail().trim().toLowerCase(),
                req.getPhone(),
                req.getSpecialization().trim(),
                req.getHospitalId().trim()
        );
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor getById(String id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found: " + id));
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepo.findAll();
    }

    @Override
    @Transactional
    public Doctor update(String id, UpdateDoctorRequest req) {
        Doctor d = getById(id);

        if (!hospitalExists(req.getHospitalId().trim())) {
            throw new InvalidHospitalException("Invalid hospital id: " + req.getHospitalId());
        }

        d.setName(req.getName().trim());
        d.setEmail(req.getEmail().trim().toLowerCase());
        d.setPhone(req.getPhone());
        d.setSpecialization(req.getSpecialization().trim());
        d.setHospitalId(req.getHospitalId().trim());

        return doctorRepo.save(d);
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!doctorRepo.existsById(id)) {
            throw new NotFoundException("Doctor not found: " + id);
        }
        doctorRepo.deleteById(id);
    }

    @Override
    public List<Doctor> search(String id, String name, String specialization, String hospitalId, String email) {
        if (id != null && !id.isBlank()) {
            return Collections.singletonList(getById(id));
        }
        if (name != null && !name.isBlank()) {
            return doctorRepo.findByNameContainingIgnoreCase(name);
        }
        if (specialization != null && !specialization.isBlank()) {
            return doctorRepo.findBySpecializationContainingIgnoreCase(specialization);
        }
        if (hospitalId != null && !hospitalId.isBlank()) {
            return doctorRepo.findByHospitalId(hospitalId);
        }
        if (email != null && !email.isBlank()) {
            return doctorRepo.findByEmailContainingIgnoreCase(email);
        }
        return doctorRepo.findAll();
    }
}
