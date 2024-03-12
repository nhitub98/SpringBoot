package com.vn.devmaster.services.learnjpa.controller;

import com.vn.devmaster.services.learnjpa.entities.Student;
import com.vn.devmaster.services.learnjpa.repository.StudentRepository;
import com.vn.devmaster.services.learnjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/std")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("/students")
    List<Student> getAllStudent(){
        return studentService.findAll();
    }
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") int id) {

    }
    @PostMapping("/student")
    public void addStudent(@RequestBody Student student) {
    }
    @PutMapping("/student")
    public void updateStudent(@RequestBody Student student) {
    }
}