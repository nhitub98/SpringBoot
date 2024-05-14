package com.example.demoproject.mapper;

import com.example.demoproject.dto.OrdersDTO;
import com.example.demoproject.dto.OrdersPaymentDTO;
import com.example.demoproject.dto.OrdersTransportDTO;
import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.OrdersDetails;
import com.example.demoproject.entities.OrdersPayment;
import com.example.demoproject.entities.OrdersTransport;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersPaymentMapper implements EntityMapper<OrdersPayment, OrdersPaymentDTO> {
    @Override
    public OrdersPayment toEntity(OrdersPaymentDTO dto)  {
        return OrdersPayment.builder()
                .id(dto.getId())
                .idord(dto.getIdord())
                .idpayment(dto.getIdpayment())
                .total(dto.getTotal())
                .notes(dto.getNotes())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public OrdersPaymentDTO toDto(OrdersPayment entity) {
        return OrdersPaymentDTO.builder()
                .id(entity.getId())
                .idord(entity.getIdord())
                .idpayment(entity.getIdpayment())
                .total(entity.getTotal())
                .notes(entity.getNotes())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public List<OrdersPayment> toEntity(List<OrdersPaymentDTO> dto) {
        List<OrdersPayment> ordersPaymentList=new ArrayList<>();
        dto.forEach(ordersPaymentDTO-> ordersPaymentList.add(toEntity(ordersPaymentDTO)));
        return ordersPaymentList;
    }


    @Override
    public List<OrdersPaymentDTO> toDto(List<OrdersPayment> entity) {
        List<OrdersPaymentDTO> ordersPaymentDTOList = new ArrayList<>();
        entity.forEach(ordersPayment-> {
            OrdersPaymentDTO ordersPaymentDTO = toDto(ordersPayment);
            ordersPaymentDTOList.add(ordersPaymentDTO);
        });
        return ordersPaymentDTOList;
    }
    }

