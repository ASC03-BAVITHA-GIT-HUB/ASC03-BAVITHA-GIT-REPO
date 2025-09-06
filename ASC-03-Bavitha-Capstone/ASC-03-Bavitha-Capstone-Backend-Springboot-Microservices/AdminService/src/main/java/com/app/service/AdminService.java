package com.app.service;

import com.app.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin register(Admin admin);
    Admin login(String emailId, String rawPassword);

    Admin getById(Long id);                      // READ by id
    List<Admin> getAll();                        // READ all
    void deleteById(Long id);                    // DELETE

}
