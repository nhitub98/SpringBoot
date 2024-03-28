package com.example.demo.demo6.dto;

import com.example.demo.demo6.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private int id;
    private String name;
    private String address;
    private ClazzDTO clazz;
    private float point;
    List<SubjectDTO> subjects = new ArrayList<>();


}
