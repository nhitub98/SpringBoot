package com.example.demoproject.repository;

import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

}

