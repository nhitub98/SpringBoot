package com.example.demoproject.repository;

import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.OrdersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailsRepository extends JpaRepository<OrdersDetails, Integer> {
}
