package com.myapp.controller;

import com.myapp.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @GetMapping("/getHTMLContent")
    public String getHTMLContent() {
        String htmlOutput = "<html><head><titile>Welcome</title></head><body><h1 style = \"color:green\">Rest API can also expose html data</h1></body></html>";
        return htmlOutput;
    }

    @GetMapping("/getEmployeesAsList")
    public List<Employee> getEmployeesAsList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Alice"));
        employees.add(new Employee(2,"Bob"));
        employees.add(new Employee(3,"Charlie"));
        return employees;
    }

    @GetMapping("/getEmployeesAsMap")
    public Map<Integer, Employee> getEmployeeAsMap() {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(1, new Employee(1, "Alice"));
        employeeMap.put(2, new Employee(2, "Bob"));
        employeeMap.put(3, new Employee(3, "Charlie"));
        return employeeMap;
    }


}
