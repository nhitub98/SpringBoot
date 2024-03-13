package com.example.demo2.repository;

import com.example.demo2.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    public Student getOne(){
        return Student.builder()
                .name("Tung")
                .address("Ha Dong")
                .build();
    }
}
