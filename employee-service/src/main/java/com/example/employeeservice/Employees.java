package com.example.employeeservice;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    private List<Employee> employeeList;

    public Employees() {
        this.employeeList = new ArrayList<>();
        initializeEmployees();
    }

    private void initializeEmployees() {
        employeeList.add(new Employee("E001", "John", "Doe", "john.doe@example.com", "Software Engineer"));
        employeeList.add(new Employee("E002", "Jane", "Smith", "jane.smith@example.com", "Product Manager"));
        employeeList.add(new Employee("E003", "Michael", "Johnson", "michael.johnson@example.com", "DevOps Engineer"));
        employeeList.add(new Employee("E004", "Sarah", "Williams", "sarah.williams@example.com", "QA Engineer"));
        employeeList.add(new Employee("E005", "David", "Brown", "david.brown@example.com", "Tech Lead"));
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
