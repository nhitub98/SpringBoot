package com.example.demoproject.mapper;

import com.example.demoproject.dto.OrdersDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.entities.Customer;
import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersMapper implements EntityMapper<Orders, OrdersDTO>{
    @Override
    public Orders toEntity(OrdersDTO dto) {
//        Timestamp ordersDate = DateUtils.stringToTimestamp(dto.getOrdersDate());

        return Orders.builder()
                .id(dto.getId())
                .idorders(dto.getIdorders())
                .ordersDate(dto.getOrdersDate())
                .idcustomer(dto.getIdcustomer())
                .totalMoney(dto.getTotalMoney())
                .notes(dto.getNotes())
                .nameReciver(dto.getNameReciver())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public OrdersDTO toDto(Orders entity) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ordersDate = dateFormat.format(entity.getOrdersDate());

        return OrdersDTO.builder()
                .id(entity.getId())
                .idorders(entity.getIdorders())
                .ordersDate(entity.getOrdersDate())
                .idcustomer(entity.getIdcustomer())
                .totalMoney(entity.getTotalMoney())
                .notes(entity.getNotes())
                .nameReciver(entity.getNameReciver())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public List<Orders> toEntity(List<OrdersDTO> dto) {
        List<Orders> ordersList = new ArrayList<>();
        dto.forEach(ordersDTO -> ordersList.add(toEntity(ordersDTO)));
        return ordersList;
    }

    @Override
    public List<OrdersDTO> toDto(List<Orders> entity) {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        entity.forEach(orders-> {
            OrdersDTO ordersDTO = toDto(orders);
            ordersDTOList.add(ordersDTO);
        });
        return ordersDTOList;
    }
}
