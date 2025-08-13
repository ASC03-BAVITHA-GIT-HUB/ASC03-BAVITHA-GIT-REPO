package com.myapp.controller;

import com.myapp.entity.Register;
import com.myapp.service.RegisterServiceSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class RegisterController {

    @Autowired
    private RegisterServiceSpring registerServiceSpring;

    @PostMapping("/register")
    public String register(@RequestBody Register register) {
        return registerServiceSpring.register(register.getUsername(), register.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody Register register) {
        return registerServiceSpring.login(register.getUsername(), register.getPassword());
    }
}
