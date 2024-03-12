package com.vn.devmaster.services.learnjpa.service;

import com.vn.devmaster.services.learnjpa.entities.Student;
import com.vn.devmaster.services.learnjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }
    public Student findById(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }
    public List<Student> getAllStudents() {
        return null;
    }
    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }
    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }
    public Student getStudentById(int id) {
        return findById(id);
    }
}
