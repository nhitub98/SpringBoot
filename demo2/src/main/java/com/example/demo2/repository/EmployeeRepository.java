package com.example.demo2.repository;

import com.example.demo2.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    public Employee findById(String id) {
        // Simulating database access
        List<Employee> employeeList = findAll();

        // Loop through the list of employees
        for (Employee employee : employeeList) {
            // Check if the current employee's ID matches the requested ID
            if (employee.getId().equals(id)) {
                return employee; // Return the employee if found
            }
        }

        return null; // Return null if no employee found with the specified ID
    }


    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Employee employee = Employee.builder()
                    .id(String.valueOf(i + 1))
                    .ten("Hien")
                    .luong(25000.f)
                    .diachi("QN")
                    .build();
            employeeList.add(employee);
        }
        return employeeList;
    }

    public Employee findOne() {
        Employee employee = Employee.builder()
                .id("1")
                .ten("Hien")
                .luong(25000.f)
                .diachi("QN")
                .build();
        return employee;
    }


}
