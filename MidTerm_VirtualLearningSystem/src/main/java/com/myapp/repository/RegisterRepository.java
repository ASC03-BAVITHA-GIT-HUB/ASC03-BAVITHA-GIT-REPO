package com.myapp.repository;

import com.myapp.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findByUsername (String username);
    Register findByUsernameAndPassword (String username, String password);

}
