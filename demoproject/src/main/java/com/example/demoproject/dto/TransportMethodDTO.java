package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransportMethodDTO {
    private Integer id;
    private String name;
    private String notes;
    private String createdDate;
    private String updatedDate;
    private int isactive;
}
