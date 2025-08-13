package com.myapp.controller;

import com.myapp.entity.Course;
import com.myapp.service.CourseServiceSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")

public class CourseController {

    @Autowired
    private  CourseServiceSpring courseService;


    @PostMapping("/addcourse")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // Get course by ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    // Get all courses
    @GetMapping("/allcourses")
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    // Search by author
    @GetMapping("/byauthor")
    public List<Course> searchByAuthorName(@RequestBody String authorName) {
        return courseService.searchByAuthorName(authorName);
    }

    @GetMapping("/byname")
    public List<Course> searchByName(@RequestBody String courseName) {
        return courseService.searchByCourseName(courseName);
    }

}
