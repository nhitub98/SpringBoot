package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersPaymentDTO {
    private Integer id;
    private Integer idord;
    private Integer idpayment;
    private Integer total;
    private Integer notes;
    private String status;


}
