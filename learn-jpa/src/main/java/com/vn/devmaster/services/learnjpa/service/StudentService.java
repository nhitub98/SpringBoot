package com.vn.devmaster.services.learnjpa.service;

import com.vn.devmaster.services.learnjpa.dto.StudentDTO;
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
        return studentRepository.findById(id).orElse(null);
    }
    public List<Student> getAllStudents() {
//        return null;
        return studentRepository.findAll();
    }
    public Student getStudentById(int id) {
        return findById(id);
    }

//    public void save(Student student) {
//        studentRepository.save(student);
//    }

    public String save(StudentDTO studentDTO){
        Student student=new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAddress(studentDTO.getAddress());
        student.setClazz(studentDTO.getClazz());
        studentRepository.save(student);
        return "Thêm thành công";
    }

//
//    public Student update1(int id, Student student) {
//        Student fromDB = studentRepository.findById(id).orElse(null);
//        if (fromDB == null) {
//            return null;
//        }
//        fromDB.setFirstName(student.getFirstName());
//        fromDB.setLastName(student.getLastName());
//        fromDB.setAddress(student.getAddress());
//        fromDB.setClazz(student.getClazz());
//        return studentRepository.save(fromDB);
//    }

    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    public String update(int id, StudentDTO studentDTO) {
        boolean existsStudent=studentRepository.existsById(id);
        if(!existsStudent) return "Không có sinh viên có id = " +id;
        Student student=new Student();
        student.setId(id);
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAddress(studentDTO.getAddress());
        student.setClazz(studentDTO.getClazz());
        studentRepository.save(student);
        return "Cập nhật thành công";
    }

    public List<Student> findByAddress(String address) {
        List<Student> students= studentRepository.findByAddress(address);
        return students;
    }
}























//    public void saveOrUpdate(Student student) {
//        studentRepository.save(student);
//    }
//    public void deleteStudentById(int id) {
//        studentRepository.deleteById(id);
//    }