package com.example.demoproject.service;

import com.example.demoproject.dto.CustomerDTO;
import com.example.demoproject.dto.OrdersDetailsDTO;
import com.example.demoproject.entities.Customer;
import com.example.demoproject.entities.OrdersDetails;
import com.example.demoproject.mapper.CustomerMapper;
import com.example.demoproject.mapper.OrdersDetailsMapper;
import com.example.demoproject.repository.CustomerRepository;
import com.example.demoproject.repository.OrdersDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersDetailsService {
    private final OrdersDetailsRepository ordersDetailsRepository;
    private final OrdersDetailsMapper ordersDetailsMapper;

    public List<OrdersDetailsDTO> findAllOrdersDetails() {
        List<OrdersDetails> ordersDetails = ordersDetailsRepository.findAll();
        return ordersDetailsMapper.toDto(ordersDetails);
    }

    public OrdersDetailsDTO findOrdersDetailsById(int id) {
        OrdersDetails ordersDetails= ordersDetailsRepository.findById(id).orElse(null);
        if (ordersDetails == null) {
            return null;
        }
        return ordersDetailsMapper.toDto(ordersDetails);
    }
}
