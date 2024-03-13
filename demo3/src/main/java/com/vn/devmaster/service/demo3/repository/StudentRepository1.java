package com.vn.devmaster.service.demo3.repository;

import com.vn.devmaster.service.demo3.entities.Student1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentRepository1 {
    public List<Student1> findAll() {
        List<Student1> student1s = new ArrayList<>();
        student1s.add(Student1
                .builder()
                .address("qn")
                .id("01") //nhap trong post man de check
                .firstName("Hien")
                .lastName("Quang")
                .build());
        return student1s;
    }
}
