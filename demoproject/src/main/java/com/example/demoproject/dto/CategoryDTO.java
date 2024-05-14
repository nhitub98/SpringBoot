package com.example.demoproject.dto;

import com.example.demoproject.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDTO  {
    private Integer id;
    private Integer idparent;
    private String name;
    private String notes;
    private String icon;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;
    private int isactive;
    }

