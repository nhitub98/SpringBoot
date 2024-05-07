package com.example.demoproject.repository;

import com.example.demoproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
        @Query(value = "SELECT p FROM Product p")
        List<Product> findAll();
    }
