package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersDTO {
    private Integer id;
    private Integer idorders;
    private java.sql.Timestamp ordersDate;
    private Integer idcustomer;
    private Double totalMoney;
    private String notes;
    private String nameReciver;
    private String address;
    private String phone;

}
