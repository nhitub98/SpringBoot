package com.vn.devmaster.service.demo5.demo5.service;

import com.vn.devmaster.service.demo5.demo5.dto.StudentDTO;
import com.vn.devmaster.service.demo5.demo5.entities.Student;
import com.vn.devmaster.service.demo5.demo5.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Student> getAllStudents() {
//        return null;
        return studentRepository.findAll();
    }

    public StudentDTO getStudentById(int id) {
        return findById(id);
    }

//    public void save(Student student) {
//        studentRepository.save(student);
//    }

//    public String save(StudentDTO studentDTO){
//        Student student=new Student();
//        student.setAddress(studentDTO.getAddress());
//        student.setClazz(studentDTO.getClazz());
//        studentRepository.save(student);
//        return "Thêm thành công";
//    }

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

//    public void delete(int id) {
//        studentRepository.deleteById(id);
//    }

    public String saveStudent(Student student) {
        Student students = new Student();
        student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        student.setAddress(student.getAddress());
        student.setClazz(student.getClazz());
        student.setPoint(student.getPoint());
        studentRepository.save(student);
        return "Thêm thành công";
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public String updateStudent(int id, Student student) {
        boolean existsStudent = studentRepository.existsById(id);
        if (!existsStudent) return "Không có sinh viên có id = " + id;
        Student students = new Student();
        student.setId(id);
        student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        student.setAddress(student.getAddress());
        student.setClazz(student.getClazz());
        student.setPoint(student.getPoint());
        studentRepository.save(student);
        return "Cập nhật thành công";
    }


//    public String update(int id, StudentDTO studentDTO) {
//        boolean existsStudent=studentRepository.existsById(id);
//        if(!existsStudent) return "Không có sinh viên có id = " +id;
//        Student student=new Student();
//        student.setId(id);
//        student.setFirstName(studentDTO.getFullname());
//        student.setLastName(studentDTO.getFullname());
//        student.setAddress(studentDTO.getAddress());
//        student.setClazz(studentDTO.getClazz());
//        studentRepository.save(student);
//        return "Cập nhật thành công";
//    }

    public List<Student> findByAddress(String address) {
        List<Student> students = studentRepository.findByAddress(address);
        return students;
    }


    public Student findHighestPoint() {
        return studentRepository.findHighestPoint()
                .map(student -> Student
                        .builder()
                        .id(student.getId())
                        .firstName(student.getLastName())
                        .clazz(student.getClazz())
                        .point(student.getPoint())
                        .address(student.getAddress())
                        .build())
                .orElse(null);

    }

    public List<Student> findPointGreaterThan5() {
        return studentRepository.findPointGreaterThan5();
    }
}





