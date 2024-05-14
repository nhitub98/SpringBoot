package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentMethodDTO {
    private Integer id;
    private String name;
    private String notes;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int isactive;


}
