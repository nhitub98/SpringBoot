package com.vn.devmaster.service.demo3.controller;

import com.vn.devmaster.service.demo3.dto.StudentDTO1;
import com.vn.devmaster.service.demo3.entities.Student1;
import com.vn.devmaster.service.demo3.service.StudentService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/v1")
public class StudentController1 {
    @Autowired
    private StudentService1 studentService1;
    @GetMapping("")
    StudentDTO1 findById(@RequestParam("id") String id){
        StudentDTO1 studentDTO1 =studentService1.findById(id);
        return  studentDTO1;

    }
}
