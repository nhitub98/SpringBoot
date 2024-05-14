package com.example.demoproject.repository;

import com.example.demoproject.entities.OrdersTransport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersTransportRepository extends JpaRepository<OrdersTransport, Integer> {
}
