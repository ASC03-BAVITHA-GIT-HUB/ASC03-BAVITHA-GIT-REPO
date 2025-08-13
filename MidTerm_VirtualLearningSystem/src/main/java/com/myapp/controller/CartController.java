package com.myapp.controller;

import com.myapp.entity.Course;
import com.myapp.service.CartService;
import com.myapp.service.CourseServiceSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private final CartService cartService;
    private final CourseServiceSpring courseService;

    public CartController(CartService cartService, CourseServiceSpring courseService)
    {
        this.cartService = cartService;
        this.courseService = courseService;
    }

    @PostMapping("/add/{courseId}")
    public String addToCart(@PathVariable Long courseId) {
        Course course = courseService.getById(courseId);
        if (course != null) {
            cartService.addToCart(course);
            return "Course added to cart.";
        }
        return "Course not found.";
    }

    @DeleteMapping("/remove/{courseId}")
    public String removeFromCart(@PathVariable Long courseId) {
        boolean removed = cartService.removeFromCart(courseId);
        return removed ? "Course removed from cart." : "Course not in cart.";
    }

    @GetMapping("/view")
    public List<Course> viewCart() {
        return cartService.viewCart();
    }
}

