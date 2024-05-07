package com.example.demoproject.repository;

import com.example.demoproject.entities.Product;
import com.example.demoproject.entities.TransportMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportMethodRepository extends JpaRepository<TransportMethod, Integer> {

}
