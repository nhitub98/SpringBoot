package com.vn.devmaster.service.demo3.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student1 {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
}
