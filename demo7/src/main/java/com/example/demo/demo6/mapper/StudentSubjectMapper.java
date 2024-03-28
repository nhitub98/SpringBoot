package com.example.demo.demo6.mapper;

import com.example.demo.demo6.dto.StudentSubjectDTO;
import com.example.demo.demo6.dto.SubjectDTO;
import com.example.demo.demo6.entities.StudentSubject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudentSubjectMapper implements EntityMapper<StudentSubject, StudentSubjectDTO> {
    @Override
    public StudentSubject toEntity(StudentSubjectDTO dto) {
        return StudentSubject.builder()
                .point((float) dto.getPoint())
                .build();
    }

    @Override
    public StudentSubjectDTO toDto(StudentSubject entity) {
        return StudentSubjectDTO.builder()
                .point(entity.getPoint())
                .build();
    }

    @Override
    public List<StudentSubject> toEntity(List<StudentSubjectDTO> dto) {
            List<StudentSubject> subjects = new ArrayList<>();
            dto.forEach(studentSubjectDto ->{
                subjects.add(toEntity(studentSubjectDto));
            });
            return subjects;
    }

    @Override
    public List<StudentSubjectDTO> toDto(List<StudentSubject> entity) {
        List<StudentSubjectDTO> dtos = new ArrayList<>();
        entity.forEach(item->{
            StudentSubjectDTO studentSubjectDTO=toDto(item);
            dtos.add(studentSubjectDTO);
        });
        return dtos;
    }
}
