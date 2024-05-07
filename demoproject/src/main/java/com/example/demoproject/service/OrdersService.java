package com.example.demoproject.service;

import com.example.demoproject.dto.OrdersDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Customer;
import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.mapper.CustomerMapper;
import com.example.demoproject.mapper.OrdersMapper;
import com.example.demoproject.repository.CustomerRepository;
import com.example.demoproject.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public List<OrdersDTO> findAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersMapper.toDto(ordersList);
    }

    public OrdersDTO findOrdersById(int id) {
        Orders orders= ordersRepository.findById(id).orElse(null);
        if (orders == null) {
            return null;
        }
        return ordersMapper.toDto(orders);
    }

}
