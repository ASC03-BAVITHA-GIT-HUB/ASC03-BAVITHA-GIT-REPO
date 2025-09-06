package com.app.repository;

import com.app.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    Optional<Patient> findTopByIdStartingWithOrderByIdDesc(String prefix);

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, String id);

    List<Patient> findByNameContainingIgnoreCase(String name);
    List<Patient> findByCityContainingIgnoreCase(String city);
    List<Patient> findByBloodGroupIgnoreCase(String bloodGroup);
    List<Patient> findByEmailContainingIgnoreCase(String email);
}
