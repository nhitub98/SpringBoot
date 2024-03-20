package com.example.demo.demo6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private int id;
    private String name;
    private String address;
    private ClazzDTO clazz;
}
