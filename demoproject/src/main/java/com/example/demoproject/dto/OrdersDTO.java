package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersDTO {
    private Integer id;
    private Integer idorders;
    private LocalDateTime ordersDate;
    private Integer idcustomer;
    private Double totalMoney;
    private String notes;
    private String nameReciver;
    private String address;
    private String phone;
    private int status;
    private List<OrdersDetailsDTO> ordersDetailsDTOList;
    private OrdersPaymentDTO ordersPaymentDTO;
    private OrdersTransportDTO ordersTransportDTO;

}
