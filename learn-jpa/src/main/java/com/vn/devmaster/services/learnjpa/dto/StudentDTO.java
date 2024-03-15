package com.vn.devmaster.services.learnjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String clazz;
}