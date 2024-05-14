package com.example.demoproject.mapper;

import com.example.demoproject.dto.OrdersTransportDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.entities.OrdersPayment;
import com.example.demoproject.entities.OrdersTransport;
import com.example.demoproject.entities.PaymentMethod;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersTransportMapper implements EntityMapper<OrdersTransport, OrdersTransportDTO> {
    @Override
    public OrdersTransport toEntity(OrdersTransportDTO dto)  {
        return OrdersTransport.builder()
                .id(dto.getId())
                .idord(dto.getIdord())
                .idtransport(dto.getIdtransport())
                .total(dto.getTotal())
                .notes(dto.getNotes())
                .build();
    }

    @Override
    public OrdersTransportDTO toDto(OrdersTransport entity) {
         return OrdersTransportDTO.builder()
                 .id(entity.getId())
                .idord(entity.getIdord())
                .idtransport(entity.getIdtransport())
                .total(entity.getTotal())
                .notes(entity.getNotes())
                .build();
    }

    @Override
    public List<OrdersTransport> toEntity(List<OrdersTransportDTO> dto) {
        List<OrdersTransport> ordersTransportList= new ArrayList<>();
        dto.forEach(ordersTransportDTO -> ordersTransportList.add(toEntity(ordersTransportDTO)));
        return ordersTransportList;
    }

    @Override
    public List<OrdersTransportDTO> toDto(List<OrdersTransport> entity) {
        List<OrdersTransportDTO> ordersTransportList = new ArrayList<>();
        entity.forEach(ordersTransport -> ordersTransportList.add(toDto(ordersTransport)));
        return ordersTransportList;
    }
}
