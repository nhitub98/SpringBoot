package com.example.demo.demo6.controller;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.dto.SubjectDTO;
import com.example.demo.demo6.projection.IAvgPoint;
import com.example.demo.demo6.projection.IStudentPoint;
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
    String saveStudent(@RequestBody StudentDTO student) {
        String message = studentService.saveStudent(student);
        return message;
    }

    @PutMapping("/students")
    String updateStudent(@RequestParam("id") int id, @RequestBody StudentDTO studentDTO) {
        String message = studentService.updateStudent(id, studentDTO);
        return message;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Xóa thành công";
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

    @GetMapping("/{id}")
    List<IStudentPoint> getStudentById2() {
        return studentService.findStudentById2();
    }

    @GetMapping("/FindById/{id}")
    List<IStudentPoint> getStudentById3(@PathVariable int id) {

        return studentService.findStudentById3(id);
    }

    @GetMapping("getAllSubject")
    List<SubjectDTO> getAllSubject() {

        return studentService.getAllSubject();
    }


    @GetMapping("/findBySubject/{id}")
    public StudentDTO getStudentPoints(@PathVariable int id) {

        return studentService.groupBySubject(id);
    }

    @GetMapping("/findAvgPoint/{id}")
    List<IAvgPoint> getAvgPoint(@PathVariable int id){

        return studentService.findAvgPoint(id);
    }
}





