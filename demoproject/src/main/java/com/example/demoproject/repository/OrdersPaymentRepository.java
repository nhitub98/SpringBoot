package com.example.demoproject.repository;

import com.example.demoproject.entities.OrdersDetails;
import com.example.demoproject.entities.OrdersPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersPaymentRepository extends JpaRepository<OrdersPayment, Integer> {
}
