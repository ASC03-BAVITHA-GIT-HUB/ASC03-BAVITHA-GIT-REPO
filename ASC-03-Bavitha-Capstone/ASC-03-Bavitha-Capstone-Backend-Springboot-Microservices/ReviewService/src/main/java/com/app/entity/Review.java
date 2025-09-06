package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Review")
public class Review {


    @Id
    @Column(name = "ReviewId", length = 5, nullable = false, updatable = false, unique = true)
    private String id;


    @Column(name = "HospitalId", length = 5, nullable = false)
    @NotBlank(message = "HospitalId is required")
    private String hospitalId;


    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "Comment", length = 1000, nullable = false)
    @NotBlank(message = "Comment is required")
    private String comment;

    @Column(name = "CreatedAt", nullable = false)
    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    protected Review() {}

    public Review(String id, String hospitalId, Integer rating, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        if (this.id != null) throw new IllegalStateException("ID cannot be modified");
        this.id = id;
    }

    public String getId() { return id; }
    public String getHospitalId() { return hospitalId; }
    public Integer getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

