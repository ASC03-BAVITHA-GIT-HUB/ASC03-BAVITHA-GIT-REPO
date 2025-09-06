package com.app.repository;

import com.app.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository  extends JpaRepository<Admin, Long> {
    boolean existsByEmailId(String emailId);      // for register duplicate check
    Optional<Admin> findByEmailId(String emailId); // for login (load hash)

}
