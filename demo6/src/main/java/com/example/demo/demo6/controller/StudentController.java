package com.example.demo.demo6.controller;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/std")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public List<StudentDTO> getAllStudent() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping("/student")
    public String addStudent(@RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(studentDTO);
        return "Thêm thành công";
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Xóa thành công";
    }

    @PutMapping("/students/{id}")
    String updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {
        String message = studentService.updateStudent(id, studentDTO);
        return message;
    }

    @GetMapping("/find-by-address")
    List<StudentDTO> findByAddress(@RequestParam("address") String address) {
        return studentService.findByAddress(address);
    }

    @GetMapping("/find-highest-point")
    public List<StudentDTO> findHighestPoint() {
        return studentService.findHighestPoint();
    }

    @GetMapping("/find-point-greater-than-5")
    public List<StudentDTO> findPointGreaterThan5() {

        return studentService.findPointGreaterThan5();
    }
}
