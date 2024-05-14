package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersDetailsDTO {
    private Integer id;
    private Integer idord;
    private Integer idproduct;
    private Double price;
    private Integer qty;

}
