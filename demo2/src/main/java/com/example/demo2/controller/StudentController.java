package com.example.demo2.controller;
import com.example.demo2.domain.Student;
import com.example.demo2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/student")
    Student getOneStudent(){
        return studentService.getOne();
    }
}
