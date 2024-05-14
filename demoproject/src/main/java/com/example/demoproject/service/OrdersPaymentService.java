package com.example.demoproject.service;

import com.example.demoproject.dto.OrdersDetailsDTO;
import com.example.demoproject.dto.OrdersPaymentDTO;
import com.example.demoproject.entities.OrdersDetails;
import com.example.demoproject.entities.OrdersPayment;
import com.example.demoproject.mapper.OrdersDetailsMapper;
import com.example.demoproject.mapper.OrdersPaymentMapper;
import com.example.demoproject.repository.OrdersDetailsRepository;
import com.example.demoproject.repository.OrdersPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersPaymentService {
    private final OrdersPaymentRepository ordersPaymentRepository;
    private final OrdersPaymentMapper ordersPaymentMapper;

    public List<OrdersPaymentDTO> findAllOrdersPayment() {
        List<OrdersPayment> ordersPaymentList = ordersPaymentRepository.findAll();
        return ordersPaymentMapper.toDto(ordersPaymentList);
    }

    public OrdersPaymentDTO findOrdersPaymentById(int id) {
        OrdersPayment ordersPayment= ordersPaymentRepository.findById(id).orElse(null);
        if (ordersPayment == null) {
            return null;
        }
        return ordersPaymentMapper.toDto(ordersPayment);
    }
}
