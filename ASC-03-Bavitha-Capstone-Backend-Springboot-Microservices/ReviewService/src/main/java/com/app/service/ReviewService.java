package com.app.service;

import com.app.dto.CreateReviewRequest;
import com.app.entity.Review;

import java.util.List;

public interface ReviewService {
    Review create(CreateReviewRequest req);
    Review getById(String id);
    List<Review> getAll();
    void delete(String id);
}

