package com.myapp.service;

import com.myapp.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseServiceSpring  {
    List<Course> findAll();
    List<Course> searchByAuthorName(String authorName);
    List<Course> searchByCourseName(String courseName);
    Course getById(Long id);
    Course addCourse(Course course);


}
