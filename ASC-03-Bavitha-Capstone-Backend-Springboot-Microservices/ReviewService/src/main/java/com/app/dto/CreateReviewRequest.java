package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateReviewRequest {
    @NotBlank(message = "HospitalId is required")
    private String hospitalId;


    private Integer rating;

    @NotBlank(message = "Comment is required")
    private String comment;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    public CreateReviewRequest() {}

    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

