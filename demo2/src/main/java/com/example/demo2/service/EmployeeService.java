package com.example.demo2.service;

import com.example.demo2.domain.Employee;
import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    public EmployeeDTO findOne() {

        Employee employee = employeeRepository.findOne();


        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setTen(employee.getTen());
        employeeDTO.setLuong(employee.getLuong());


        employee.setLuong(0); //set lương về 0

        return employeeDTO;
    }

    public Employee findById(String id) {

        return employeeRepository.findById(id);
    }
}
