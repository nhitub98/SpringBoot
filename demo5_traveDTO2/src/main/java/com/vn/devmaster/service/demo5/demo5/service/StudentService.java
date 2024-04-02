package com.vn.devmaster.service.demo5.demo5.service;

import com.vn.devmaster.service.demo5.demo5.dto.StudentDTO;
import com.vn.devmaster.service.demo5.demo5.entities.Student;
import com.vn.devmaster.service.demo5.demo5.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> findAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .clazz(student.getClazz())
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }

    public StudentDTO findById(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getFirstName() + " " + student.getLastName())
                .clazz(student.getClazz())
                .build();
    }

//    public List<Student> getAllStudents() {
////        return null;
//        return studentRepository.findAll();
//    }

    public StudentDTO getStudentById(int id) {
        return findById(id);
    }


    public String saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAddress(studentDTO.getAddress());
        student.setClazz(studentDTO.getClazz());
        student.setPoint(studentDTO.getPoint());
        student.setClazzId(studentDTO.getClazzId());
        studentRepository.save(student);
        return "Thêm thành công";
    }


    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public String updateStudent(int id, StudentDTO studentDTO) {
        boolean existsStudent = studentRepository.existsById(id);
        if (!existsStudent) return "Không có sinh viên có id = " + id;

        Student student = new Student();
        student.setId(id);
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAddress(studentDTO.getAddress());
        student.setClazz(studentDTO.getClazz());
        student.setClazzId(studentDTO.getClazzId());
        student.setPoint(studentDTO.getPoint());
        studentRepository.save(student);
        return "Cập nhật thành công";
    }

    public List<StudentDTO> findByAddress(String address) {
        return studentRepository.findByAddress(address).stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .address(student.getAddress())
                        .clazz(student.getClazz())
                        .point(student.getPoint())
                        .clazzId(student.getClazzId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<StudentDTO> findHighestPoint() {
        List<Student> studentList = studentRepository.findHighestPoint();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .clazz(student.getClazz())
                        .point(student.getPoint())
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }

    public List<StudentDTO> findPointGreaterThan5() {
        List<Student> studentList = studentRepository.findHighestPoint();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .clazz(student.getClazz())
                        .point(student.getPoint())
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }
}






