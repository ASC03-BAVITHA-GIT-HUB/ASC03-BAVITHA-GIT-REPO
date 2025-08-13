package com.myapp.service;

import com.myapp.entity.Course;

import java.util.List;

public interface CartService {
    void addToCart(Course course);

    boolean removeFromCart(Long courseId);

    List<Course> viewCart();

}
