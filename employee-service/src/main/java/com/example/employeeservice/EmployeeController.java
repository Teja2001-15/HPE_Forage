package com.example.employeeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {
    private Employees employees;

    public EmployeeController() {
        this.employees = new Employees();
    }

    @GetMapping("/employees")
    public Map<String, Object> getEmployees() {
        Map<String, Object> response = new HashMap<>();
        response.put("Employees", employees.getEmployeeList());
        return response;
    }
}
