package com.example.demo2.springboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Company {
    @Qualifier("employee")
    @Autowired
    private IPerson employeeDependence;

    public IPerson getEmployeeDependence() {
        return employeeDependence;
    }

    public void setEmployeeDependence(IPerson employeeDependence) {

        this.employeeDependence = employeeDependence;
    }

}
