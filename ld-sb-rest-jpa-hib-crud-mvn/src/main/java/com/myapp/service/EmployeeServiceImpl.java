package com.myapp.service;

import com.myapp.entity.EmployeeEntity;
import com.myapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
       this.employeeRepository=  employeeRepository;
    }


        @Override
        public List<EmployeeEntity> getAllEmployees () {
            return Collections.emptyList();
        }
    }
