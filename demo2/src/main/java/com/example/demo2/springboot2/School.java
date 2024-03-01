package com.example.demo2.springboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class School {
    @Qualifier("student")
    @Autowired
    private IPerson studentDependence;

    public IPerson getStudentDependence() {
        return studentDependence;
    }

    public void setStudentDependence(IPerson studentDependence) {
        this.studentDependence = studentDependence;
    }

}
