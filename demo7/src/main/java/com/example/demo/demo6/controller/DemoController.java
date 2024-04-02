package com.example.demo.demo6.controller;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.dto.SubjectDTO;
import com.example.demo.demo6.entities.Student;
import com.example.demo.demo6.projection.IAvgPoint;
import com.example.demo.demo6.projection.IStudentPoint;
import com.example.demo.demo6.repository.StudentRepository;
import com.example.demo.demo6.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DemoController {
    @Autowired
    StudentService studentService;
    @GetMapping("/index")
    public String showIndex(Model model){
        model.addAttribute("name","Duy Tùng");
        return "index";
    }
    @GetMapping("/show-student")
    public String showStudent(Model model){
        Student student= new Student();
        student.setLastName("Tung");
        student.setAddress("QN");
        model.addAttribute("student",student);
        return "index";
    }
        @GetMapping("/show-avgpoint/{id}")
    public String showStudentAvg(@PathVariable int id, Model model){
        List<IAvgPoint> iAvgPoints = studentService.findAvgPoint(id);
        model.addAttribute("student", iAvgPoints.get(0));
        return "index";
    }
    @GetMapping("/findBySubject/{id}")
    public String getStudentPoints(@PathVariable int id, Model model) {
        StudentDTO studentDTO= studentService.groupBySubject(id);
        List<SubjectDTO> subjectDTOList= studentDTO.getSubjects();
        model.addAttribute("studentDTO", studentDTO);
        return "index";
    }
    @GetMapping("/find-by-address")
    public String findByAddress(@RequestParam("address") String address, Model model) {
        List<StudentDTO> studentDTOList= studentService.findByAddress(address);
        model.addAttribute("students",studentDTOList);
        return "index";
    }
    @GetMapping("/view/add-student")
    public String showAddress(Model model){
        model.addAttribute("student",new Student());
        return "addstudent";
    }
    @PostMapping("/student")
    public String addStudent(@ModelAttribute("student") StudentDTO studentDTO){
        studentService.saveStudent(studentDTO);
        return "redirect:/view/students";
    }
}
