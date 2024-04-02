package com.example.demo.demo6.mapper;

import com.example.demo.demo6.dto.SubjectDTO;
import com.example.demo.demo6.entities.Clazz;
import com.example.demo.demo6.entities.Student;
import com.example.demo.demo6.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectMapper implements EntityMapper<Subject, SubjectDTO> {


    @Override

    public Subject toEntity(SubjectDTO dto) {
        return Subject.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public SubjectDTO toDto(Subject entity) {
        SubjectDTO subjectDTO=new SubjectDTO();
        subjectDTO.setId(entity.getId());
        subjectDTO.setName(entity.getName());
        return subjectDTO;
    }

    @Override
    public List<Subject> toEntity(List<SubjectDTO> dtoList) {
        List<Subject> subjects = new ArrayList<>();
        dtoList.forEach(subjectDto -> {
            subjects.add(toEntity(subjectDto));
        });
        return subjects;
    }

    @Override
    public List<SubjectDTO> toDto(List<Subject> entity) {
        List<SubjectDTO> dtos = new ArrayList<>();
        entity.forEach(item->{
            SubjectDTO subjectDTO=toDto(item);
            dtos.add(subjectDTO);
        });
        return dtos;
    }
}
