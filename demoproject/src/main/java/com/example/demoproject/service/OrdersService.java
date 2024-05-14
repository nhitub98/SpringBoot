package com.example.demoproject.service;

import com.example.demoproject.dto.OrdersDTO;
import com.example.demoproject.dto.TransportMethodDTO;
import com.example.demoproject.entities.*;
import com.example.demoproject.mapper.CustomerMapper;
import com.example.demoproject.mapper.OrdersMapper;
import com.example.demoproject.repository.CustomerRepository;
import com.example.demoproject.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    public String saveOrders(OrdersDTO ordersDTO) {
        Orders orders = ordersMapper.toEntity(ordersDTO);
        ordersRepository.save(orders);
        return "Thêm thành công";
    }

    public String updateOrders(int id, OrdersDTO ordersDTO) {
        Orders orders =ordersRepository.findById(id).orElse(null);
        if (orders == null) {
            return "Không tìm thấy phương thức thanh toán có ID = " + id;
        }
//        orders.setIdorders(ordersDTO.getIdorders());
//        orders.setAddress(ordersDTO.getAddress());
//        orders.setNotes(ordersDTO.getNotes());
//        orders.setPhone(ordersDTO.getPhone());
//        orders.setTotalMoney(ordersDTO.getTotalMoney());
//        orders.setNameReciver(ordersDTO.getNameReciver());
//        orders.setNotes(ordersDTO.getNotes());
//        orders.setOrdersDate(Timestamp.valueOf(LocalDateTime.now()));
        orders.setStatus(ordersDTO.getStatus());
        ordersRepository.save(orders);

        return "Cập nhật đơn hàng";
    }

    public void deleteOrders(int id) {
        ordersRepository.deleteById(id);
    }
}



