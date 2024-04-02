package com.vn.devmaster.service.demo5.demo5.controller;

import com.vn.devmaster.service.demo5.demo5.dto.StudentDTO;
import com.vn.devmaster.service.demo5.demo5.entities.Student;
import com.vn.devmaster.service.demo5.demo5.service.StudentService;
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

    @DeleteMapping("students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Xóa thành công";
    }

    @PutMapping("")
    String updateStudent(@RequestParam("id") int id, @RequestBody StudentDTO studentDTO) {
        String message = studentService.updateStudent(id, studentDTO);
        return message;
    }

    @GetMapping("find-by-address")
    public List<StudentDTO> findByAddress(@RequestParam("address") String address) {
        return studentService.findByAddress(address);
    }

    @GetMapping("/find-highest-point")
    public List<StudentDTO> findHighestPoint() {
        return studentService.findHighestPoint();
    }

    @GetMapping("find-point-greater-than-5")
    public List<StudentDTO> findPointGreaterThan5() {
        return  studentService.findPointGreaterThan5();
    }
}







