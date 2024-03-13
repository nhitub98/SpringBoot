package com.vn.devmaster.service.demo3.service;

import com.vn.devmaster.service.demo3.dto.StudentDTO1;
import com.vn.devmaster.service.demo3.entities.Student1;
import com.vn.devmaster.service.demo3.repository.StudentRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService1 {
    @Autowired
    StudentRepository1 studentRepository1;
    public StudentDTO1 findById(String id) {
        List<Student1> student1s = studentRepository1.findAll();
        Student1 student1 = student1s.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(new Student1());
        if(student1.getId()==null) return null;
        StudentDTO1 studentDTO1= StudentDTO1.builder()
                .id(student1.getId())
                .name(student1.getFirstName()+" "+student1.getLastName())
                .build();
//        studentDTO1.setAddress(student1.getAddress()); hiển thị địa chỉ
        return studentDTO1;
    }
}
