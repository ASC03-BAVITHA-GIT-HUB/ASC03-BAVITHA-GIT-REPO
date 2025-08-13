package com.myapp.service;

import com.myapp.entity.Register;
import com.myapp.exceptions.UserNameAlreadyExistsException;
import com.myapp.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServcieImpl implements RegisterServiceSpring {
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public String register(String username, String password) {
        Register existing = registerRepository.findByUsername(username);
        if (existing != null) {
            throw new UserNameAlreadyExistsException("Username '" + username + "' already exists");
        }
        registerRepository.save(new Register(username, password));
        return "Registration done!!!!";
    }

    @Override
    public String login(String username, String password) {
        Register user = registerRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return "logged in successfully";
        }
        return "invalid user";
    }
}
