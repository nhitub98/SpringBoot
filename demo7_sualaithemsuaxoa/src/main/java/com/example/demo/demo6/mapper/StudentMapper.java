package com.example.demo.demo6.mapper;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.entities.Clazz;
import com.example.demo.demo6.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper implements EntityMapper<Student, StudentDTO> {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private SubjectMapper subjectMapper;


    @Override
    public Student toEntity(StudentDTO dto) {
        return Student.builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .clazz(clazzMapper.toEntity(dto.getClazz()))
                .subjects(subjectMapper.toEntity(dto.getSubjects()))
                .point(dto.getPoint())
                .build();
    }

    @Override
    public StudentDTO toDto(Student entity) {
        return StudentDTO
                .builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .name(entity.getLastName()+ " " + entity.getFirstName())
                .clazz(clazzMapper.toDto(entity.getClazz()))
                .subjects(subjectMapper.toDto(entity.getSubjects()))
                .point(entity.getPoint())
                .build();
    }

    @Override
    public List<Student> toEntity(List<StudentDTO> dto){
        List<Student> studentList = new ArrayList<>();
        dto.forEach( studentdto -> {
            studentList.add(toEntity(studentdto));
        });
        return studentList;
    }

    @Override
    public List<StudentDTO> toDto(List<Student> entity) {
        List<StudentDTO> dtos = new ArrayList<>();
        entity.forEach(student -> {
            StudentDTO studentDTO = toDto(student);
            dtos.add(studentDTO);
        });
        return dtos;
    }
}
