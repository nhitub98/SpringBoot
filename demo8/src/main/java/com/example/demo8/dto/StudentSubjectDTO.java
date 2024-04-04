package com.example.demo8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentSubjectDTO {
    private Integer idStudent;
    private Integer idSubject;
    private double point;

}

