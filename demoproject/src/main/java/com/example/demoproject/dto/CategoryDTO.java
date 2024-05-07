package com.example.demoproject.dto;

import com.example.demoproject.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
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
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
    private int isactive; //để là 0,1
    }

