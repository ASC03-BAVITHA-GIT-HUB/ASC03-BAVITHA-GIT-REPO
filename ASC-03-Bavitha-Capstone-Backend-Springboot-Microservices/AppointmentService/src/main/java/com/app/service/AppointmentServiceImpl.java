// src/main/java/com/app/service/AppointmentServiceImpl.java
package com.app.service;

import com.app.dto.CreateAppointmentRequest;
import com.app.dto.UpdateAppointmentRequest;
import com.app.entity.Appointment;
import com.app.exception.InvalidPatientIdException;
import com.app.exception.InvalidDoctorIdException;
import com.app.exception.NotFoundException;
import com.app.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public AppointmentServiceImpl(AppointmentRepository repo) {
        this.repo = repo;
    }

    private String nextAppointmentId() {
        String prefix = "A";
        String last = repo.findTopByIdStartingWithOrderByIdDesc(prefix)
                .map(Appointment::getId)
                .orElse(prefix + "0000");
        int next = Integer.parseInt(last.substring(1)) + 1;
        return String.format("%s%04d", prefix, next);
    }


    private boolean patientExists(String patientId) {
        // If your Patient entity uses @Table(name="Patients"), change to FROM Patients
        String sql = "SELECT COUNT(1) FROM Patient WHERE PatientId = :id";
        Query q = entityManager.createNativeQuery(sql).setParameter("id", patientId);
        Number n = (Number) q.getSingleResult();
        return n != null && n.longValue() > 0;
    }


    private boolean doctorExists(String doctorId) {
        // If your Doctor entity uses @Table(name="Doctor"), change to FROM Doctor
        String sql = "SELECT COUNT(1) FROM Doctor WHERE DoctorId = :id";
        Query q = entityManager.createNativeQuery(sql).setParameter("id", doctorId);
        Number n = (Number) q.getSingleResult();
        return n != null && n.longValue() > 0;
    }

    @Override
    @Transactional
    public Appointment create(CreateAppointmentRequest req) {
        final String pid = req.getPatientId().trim();
        if (!patientExists(pid)) {
            throw new InvalidPatientIdException("Invalid patient id: " + pid);
        }

        final String did = (req.getDoctorId() == null) ? null : req.getDoctorId().trim();
        if (did != null && !did.isEmpty() && !doctorExists(did)) {
            throw new InvalidDoctorIdException("Invalid doctor id: " + did);
        }

        Appointment appt = new Appointment(
                nextAppointmentId(),
                pid,
                did,
                req.getWhenAt(),
                req.getReason(),
                req.getStatus().trim()
        );
        return repo.save(appt);
    }

    @Override
    public Appointment getById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found: " + id));
    }

    @Override
    public List<Appointment> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public Appointment update(String id, UpdateAppointmentRequest req) {
        Appointment a = getById(id);

        final String pid = req.getPatientId().trim();
        if (!patientExists(pid)) {
            throw new InvalidPatientIdException("Invalid patient id: " + pid);
        }

        final String did = (req.getDoctorId() == null) ? null : req.getDoctorId().trim();
        if (did != null && !did.isEmpty() && !doctorExists(did)) {
            throw new InvalidDoctorIdException("Invalid doctor id: " + did);
        }

        a.setPatientId(pid);
        a.setDoctorId(did);
        a.setWhenAt(req.getWhenAt());
        a.setReason(req.getReason());
        a.setStatus(req.getStatus().trim());

        return repo.save(a);
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repo.existsById(id)) throw new NotFoundException("Appointment not found: " + id);
        repo.deleteById(id);
    }

    @Override
    public List<Appointment> search(String patientId, String doctorId, String status,
                                    LocalDateTime from, LocalDateTime to) {
        if (patientId != null && !patientId.trim().isEmpty()) {
            return repo.findByPatientId(patientId);
        }
        if (doctorId != null && !doctorId.trim().isEmpty()) {
            return repo.findByDoctorId(doctorId);
        }
        if (status != null && !status.trim().isEmpty()) {
            return repo.findByStatusIgnoreCase(status);
        }
        if (from != null && to != null) {
            return repo.findByWhenAtBetween(from, to);
        }
        return repo.findAll();
    }
}
