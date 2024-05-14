package com.example.demoproject.repository;

import com.example.demoproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);

}
