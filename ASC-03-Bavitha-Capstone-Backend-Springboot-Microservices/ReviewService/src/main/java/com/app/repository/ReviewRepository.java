package com.app.repository;

import com.app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findTopByIdStartingWithOrderByIdDesc(String prefix);
}

