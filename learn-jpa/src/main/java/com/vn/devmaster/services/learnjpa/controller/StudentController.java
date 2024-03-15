package com.vn.devmaster.services.learnjpa.controller;

import com.vn.devmaster.services.learnjpa.dto.StudentDTO;
import com.vn.devmaster.services.learnjpa.entities.Student;
import com.vn.devmaster.services.learnjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/std")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    List<Student> getAllStudent() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    //    @PostMapping("/student")
//    public void save(@RequestBody Student student) {
//        studentService.save(student);
//    }
//
    @PostMapping("")
    public String save(@RequestBody StudentDTO studentDTO) {
        studentService.save(studentDTO);
        return "Thêm thành công";
    }
//
//    @PutMapping("/{id}")
//    public Student update1(@PathVariable int id, @RequestBody Student student) {
//        return studentService.update1(id, student);
//    }
    @DeleteMapping("students/{id}")
    public void delete(@PathVariable int id){
        studentService.delete(id);
    }
    @PutMapping("")
    String update(@RequestParam("id") int id, @RequestBody StudentDTO studentDTO){
        String message = studentService.update(id,studentDTO);
        return message;
    }
    @GetMapping("find-by-address")
    List<Student> findByAddress(@RequestParam("address") String address){
    return studentService.findByAddress(address);
    }
}
















//    @DeleteMapping("/student/{id}")
//    public void deleteStudent(@PathVariable("id") int id) {
//        studentService.deleteStudentById(id);
//    }
//    @PostMapping("/student")
//    public void addStudent(@RequestBody Student student) {
//        studentService.saveOrUpdate(student);
//    }
//    @PutMapping("/student")
//    public void updateStudent(@RequestBody Student student) {
//        studentService.saveOrUpdate(student);
//    }
