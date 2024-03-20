package com.example.demo.demo6.service;

import com.example.demo.demo6.dto.ClazzDTO;
import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.entities.Clazz;
import com.example.demo.demo6.entities.Student;
import com.example.demo.demo6.mapper.ClazzMapper;
import com.example.demo.demo6.mapper.StudentMapper;
import com.example.demo.demo6.repository.ClazzRepository;
import com.example.demo.demo6.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClazzMapper clazzMapper;
    private final ClazzRepository clazzRepository;

    public List<StudentDTO> findAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .address(student.getAddress())
                        .clazz(clazzMapper.toDto(student.getClazz()))
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }


    public StudentDTO findById(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return studentMapper.toDto(student);
    }

    public StudentDTO getStudentById(int id) {
        return findById(id);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<StudentDTO> findByAddress(String address) {
        List<Student> students = studentRepository.findByAddress(address);
        return studentMapper.toDto(students);
    }

    public List<StudentDTO> findHighestPoint() {
        List<Student> studentList = studentRepository.findHighestPoint();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .clazz(clazzMapper.toDto(student.getClazz()))
                        .address(student.getAddress())
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }


    public List<StudentDTO> findPointGreaterThan5() {
        List<Student> studentList = studentRepository.findPointGreaterThan5();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .clazz(clazzMapper.toDto(student.getClazz()))
                        .address(student.getAddress())
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }

    public String saveStudent(StudentDTO studentDTO) {
        Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
        clazz = clazzRepository.save(clazz);
        Student student = studentMapper.toEntity(studentDTO);
        student.setClazz(clazz);
        studentRepository.save(student);
        return "Thêm thành công";
    }

    public String updateStudent(int id, StudentDTO studentDTO) {
        boolean existsStudent = studentRepository.existsById(id);
        if (!existsStudent) return "Không có sinh viên có id = " + id;
        Student student = new Student();
        student.setId(id);
        String[] chuoi = studentDTO.getName().split(" ");
        String firstName = chuoi[0];
        String lastName = "";
        if (chuoi.length > 1) {
            lastName = chuoi[chuoi.length - 1];
        }
        student.setLastName(lastName);
        student.setAddress(studentDTO.getAddress());
        Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
        student.setClazz(clazz);
        studentRepository.save(student);
        return "cập nhật thành công";
    }
}
