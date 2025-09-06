package com.app.repository;

import com.app.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    Optional<Doctor> findTopByIdStartingWithOrderByIdDesc(String prefix);

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, String id);

    List<Doctor> findByNameContainingIgnoreCase(String name);
    List<Doctor> findByEmailContainingIgnoreCase(String email);
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);
    List<Doctor> findByHospitalId(String hospitalId);
}

