package com.example.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersTransportDTO {
    private Integer id;
    private Integer idord;
    private Integer idtransport;
    private Integer total;
    private Integer notes;


}
