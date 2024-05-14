package com.example.demoproject.service;

import com.example.demoproject.dto.OrdersPaymentDTO;
import com.example.demoproject.dto.OrdersTransportDTO;
import com.example.demoproject.entities.OrdersPayment;
import com.example.demoproject.entities.OrdersTransport;
import com.example.demoproject.mapper.OrdersPaymentMapper;
import com.example.demoproject.mapper.OrdersTransportMapper;
import com.example.demoproject.repository.OrdersPaymentRepository;
import com.example.demoproject.repository.OrdersTransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersTransportService {
    private final OrdersTransportRepository ordersTransportRepository;
    private final OrdersTransportMapper ordersTransportMapper;

    public List<OrdersTransportDTO> findAllOrdersTransport() {
        List<OrdersTransport> ordersTransportList = ordersTransportRepository.findAll();
        return ordersTransportMapper.toDto(ordersTransportList);
    }

    public OrdersTransportDTO findOrdersTransportById(int id) {
        OrdersTransport ordersTransport= ordersTransportRepository.findById(id).orElse(null);
        if (ordersTransport == null) {
            return null;
        }
        return ordersTransportMapper.toDto(ordersTransport);
    }
}
