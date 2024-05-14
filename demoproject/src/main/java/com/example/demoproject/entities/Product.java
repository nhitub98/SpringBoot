package com.example.demoproject.entities;

import com.example.demoproject.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGE")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORY")
    private Category category;


    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUATITY")
    private Integer quatity;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private int isactive;

    @OneToMany
    @JoinColumn(name = "ID_PRODUCT")
    private List<ProductImages> productImages = new ArrayList<>();






}
