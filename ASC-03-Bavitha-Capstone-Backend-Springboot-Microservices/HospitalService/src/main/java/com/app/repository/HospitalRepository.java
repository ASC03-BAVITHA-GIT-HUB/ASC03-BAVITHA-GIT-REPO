package com.app.repository;

import com.app.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, String> {


    Optional<Hospital> findTopByIdStartingWithOrderByIdDesc(String prefix);


    List<Hospital> findByNameContainingIgnoreCase(String name);
    List<Hospital> findByCityContainingIgnoreCase(String city);

    boolean existsByNameIgnoreCase(String name);
    boolean existsByEmailIgnoreCase(String email);
}

