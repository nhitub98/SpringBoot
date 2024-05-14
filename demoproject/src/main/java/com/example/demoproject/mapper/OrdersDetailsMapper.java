package com.example.demoproject.mapper;

import com.example.demoproject.dto.OrdersDTO;
import com.example.demoproject.dto.OrdersDetailsDTO;
import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.OrdersDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersDetailsMapper implements EntityMapper<OrdersDetails, OrdersDetailsDTO> {
    @Override
    public OrdersDetails toEntity(OrdersDetailsDTO dto) {
        return OrdersDetails.builder()
                .id(dto.getId())
                .idord(dto.getIdord())
                .idproduct(dto.getIdproduct())
                .price(dto.getPrice())
                .qty(dto.getQty())
                .build();
    }

    @Override
    public OrdersDetailsDTO toDto(OrdersDetails entity) {
        return OrdersDetailsDTO.builder()
                .id(entity.getId())
                .idord(entity.getIdord())
                .idproduct(entity.getIdproduct())
                .price(entity.getPrice())
                .qty(entity.getQty())
                .build();
    }

    @Override
    public List<OrdersDetails> toEntity(List<OrdersDetailsDTO> dto) {
        List<OrdersDetails> ordersDetails=new ArrayList<>();
        dto.forEach(ordersDetailsDTO -> ordersDetails.add(toEntity(ordersDetailsDTO)));
        return ordersDetails;
    }

    @Override
    public List<OrdersDetailsDTO> toDto(List<OrdersDetails> entity) {
        List<OrdersDetailsDTO> ordersDetailsDTOS = new ArrayList<>();
        entity.forEach(ordersDetails-> {
            OrdersDetailsDTO ordersDetailsDTO = toDto(ordersDetails);
            ordersDetailsDTOS.add(ordersDetailsDTO);
        });
        return ordersDetailsDTOS;
    }
    }

