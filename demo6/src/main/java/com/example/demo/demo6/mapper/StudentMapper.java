package com.example.demo.demo6.mapper;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.entities.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper implements EntityMapper<Student, StudentDTO> {
    private final ClazzMapper clazzMapper;

    public StudentMapper(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public Student toEntity(StudentDTO dto) {
        return Student
                .builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .firstName(dto.getName())
                .lastName(dto.getName())
                .clazz(clazzMapper.toEntity(dto.getClazz()))
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
                .build();
    }

    @Override
    public List<Student> toEntity(List<StudentDTO> dto) {
        return null;
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
