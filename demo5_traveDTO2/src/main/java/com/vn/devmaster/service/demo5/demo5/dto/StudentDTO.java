package com.vn.devmaster.service.demo5.demo5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String name;
    private String address;
    private String clazz;
    private Double point;
    private Integer clazzId;


}

