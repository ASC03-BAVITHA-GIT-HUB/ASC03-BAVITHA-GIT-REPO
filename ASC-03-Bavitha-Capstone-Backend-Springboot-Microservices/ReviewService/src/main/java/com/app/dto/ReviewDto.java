package com.app.dto;

import java.time.LocalDateTime;

public class ReviewDto {
    private final String id;
    private final String hospitalId;
    private final Integer rating;
    private final String comment;
    private final LocalDateTime createdAt;

    public ReviewDto(String id, String hospitalId, Integer rating, String comment, LocalDateTime createdAt) {
        this.id = id; this.hospitalId = hospitalId; this.rating = rating;
        this.comment = comment; this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public String getHospitalId() { return hospitalId; }
    public Integer getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

