package com.myapp.repository;

import com.myapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByAuthorName(String authorName);
    List<Course> findByCourseName(String courseName);
}
