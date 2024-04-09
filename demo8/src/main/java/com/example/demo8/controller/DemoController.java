package com.example.demo8.controller;
import com.example.demo8.dto.ClazzDTO;
import com.example.demo8.dto.StudentDTO;
import com.example.demo8.dto.SubjectDTO;
import com.example.demo8.entities.Clazz;
import com.example.demo8.entities.Student;
import com.example.demo8.mapper.ClazzMapper;
import com.example.demo8.projection.IAvgPoint;
import com.example.demo8.repository.ClazzRepository;
import com.example.demo8.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DemoController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    ClazzRepository clazzRepository;
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
        return "findavgpoint";
    }
    @GetMapping("/findBySubject/{id}")
    public String getStudentPoints(@PathVariable int id, Model model) {
        StudentDTO studentDTO= studentService.groupBySubject(id);
        List<SubjectDTO> subjectDTOList= studentDTO.getSubjects();
        model.addAttribute("studentDTO", studentDTO);
        return "findBySubject";
    }
    @GetMapping("/find-by-address")
    public String findByAddress(@RequestParam("address") String address, Model model) {
        List<StudentDTO> studentDTOList= studentService.findByAddress(address);
        model.addAttribute("students",studentDTOList);
        return "findbyaddress";
    }

    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) { //hiển thị form add student
        model.addAttribute("student", new StudentDTO());
        return "formaddstudent";
    }


    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute("student") StudentDTO studentDTO, Model model) {
        if (studentDTO != null && studentDTO.getClazz() != null) {
            Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
            clazz = clazzRepository.save(clazz);
            model.addAttribute("clazz",new ClazzDTO());
            List<Clazz> allClasses = clazzRepository.findAll();  // Assuming you have a method to retrieve all classes
            model.addAttribute("classes", allClasses);  // Add list of classes to model
            studentService.saveStudent(studentDTO);
        }
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateStudentForm(@PathVariable int id, Model model) {
        StudentDTO studentDTO = studentService.findById(id);
        model.addAttribute("student", studentDTO);
        Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
        //  Lấy danh sách tất cả các lớp học có sẵn
        List<Clazz> allClasses = clazzRepository.findAll();
        //  Thêm danh sách lớp học vào model để hiển thị trong dropdown
        model.addAttribute("classes", allClasses);
        return "formeditstudent";  // Render template "formeditstudent"
    }

    @PutMapping("/edit/{id}")
    public String updateStudent(@ModelAttribute("student") StudentDTO studentDTO, Model model, int id) {
        {
            Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());  // Assuming clazzMapper converts DTO to entity
            clazz = clazzRepository.save(clazz);
            studentService.updateStudent(id, studentDTO);  // Update student using id and DTO
        }
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String findAllStudent(Model model){
        List<StudentDTO> studentDTOList = studentService.findAll();
        model.addAttribute("students", studentDTOList);
        return "findall";
    }



    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}

