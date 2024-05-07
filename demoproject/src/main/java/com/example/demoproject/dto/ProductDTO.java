package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private String notes;
    private String image;
    private CategoryDTO idcategory;
    private Double price;
    private Integer quatity;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
    private int isactive;
    private List<ProductImagesDTO> productImagesDTOS;

}
