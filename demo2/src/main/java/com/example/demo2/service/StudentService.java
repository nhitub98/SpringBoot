package com.example.demo2.service;

import com.example.demo2.domain.Student;
import com.example.demo2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student getOne() {
        Student student = studentRepository.getOne();
        return student;
    }

}
