package com.example.demo2.controller;

import com.example.demo2.domain.Employee;
import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") String id) {
        return employeeService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/findOne")
    public EmployeeDTO findOne() {
        return employeeService.findOne();
    }
}
