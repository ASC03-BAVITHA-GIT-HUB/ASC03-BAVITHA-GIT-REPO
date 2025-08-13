package com.myapp.service;

import com.myapp.entity.Course;
import com.myapp.exceptions.CourseAlreadyExistsException;
import com.myapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceSpring {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> searchByAuthorName(String authorName) {
        return courseRepository.findByAuthorName(authorName);
    }

    @Override
    public List<Course> searchByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course addCourse(Course course) {
        if (courseRepository.findByCourseName(course.getCourseName()) != null) {
            throw new CourseAlreadyExistsException(
                    "Course with name  '" + course.getCourseName() + "' already exists"
            );
        }
        return courseRepository.save(course);
    }

}
