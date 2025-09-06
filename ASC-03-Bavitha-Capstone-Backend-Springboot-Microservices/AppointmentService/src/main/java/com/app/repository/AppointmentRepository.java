package com.app.repository;

import com.app.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    Optional<Appointment> findTopByIdStartingWithOrderByIdDesc(String prefix);

    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByStatusIgnoreCase(String status);
    List<Appointment> findByWhenAtBetween(LocalDateTime from, LocalDateTime to);
}

