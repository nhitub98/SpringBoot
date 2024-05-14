package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone;
    private LocalDateTime createdDate;
    private int isactive;

}
