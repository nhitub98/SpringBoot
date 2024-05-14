package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Integer idCategory;
    private CategoryDTO categoryDTO;
    private Double price;
    private Integer quatity;
//    private String createdDate;
//    private String updatedDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;
    private int isactive;

    List<ProductImagesDTO> productImages = new ArrayList<>();
}
