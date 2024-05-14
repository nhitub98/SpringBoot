package com.example.demoproject.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders_details")
public class OrdersDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IDORD")
    private Integer idord;

    @Column(name = "IDPRODUCT")
    private Integer idproduct;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QTY")
    private Integer qty;


}
