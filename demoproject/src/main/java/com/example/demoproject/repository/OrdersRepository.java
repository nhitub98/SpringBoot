package com.example.demoproject.repository;

import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query(value = " SELECT  idorders, orders_date,  total_money, notes, name_reciver, address, phone\n" +
            "    FROM orders\n" +
            "    WHERE ID = :id", nativeQuery = true)
    List<Orders> findAllOrders();
}
