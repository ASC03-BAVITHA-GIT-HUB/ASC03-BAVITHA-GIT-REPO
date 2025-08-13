package com.myapp.service;

import com.myapp.entity.Course;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    private final List<Course> cart = new ArrayList<>();

    // Add course to cart
    public void addToCart(Course course) {
        cart.add(course);
    }

    // Remove course from cart by ID
    public boolean removeFromCart(Long courseId) {
        return cart.removeIf(course -> course.getId().equals(courseId));
    }

    // View all items in cart
    public List<Course> viewCart() {
        return new ArrayList<>(cart);
    }
}
