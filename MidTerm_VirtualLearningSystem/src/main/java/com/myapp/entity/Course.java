package com.myapp.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CourseName", nullable = false)
    private String courseName;

    @Column(name = "AuthorName", nullable = false)
    private String authorName;


    @Column(name = "DurationInHours", nullable = false)
    private Integer durationInHours;


    @Column(name = "Availability", nullable = false)
    private Boolean availability;

    public Course(){

    }

    public Course(Long id, String courseName, String authorName, Integer durationInHours, Boolean availability) {
        this.id = id;
        this.courseName = courseName;
        this.authorName = authorName;
        this.durationInHours = durationInHours;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(Integer durationInHours) {
        this.durationInHours = durationInHours;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
