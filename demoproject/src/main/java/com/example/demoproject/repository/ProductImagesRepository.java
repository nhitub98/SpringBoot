package com.example.demoproject.repository;

import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.entities.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Integer> {

}
