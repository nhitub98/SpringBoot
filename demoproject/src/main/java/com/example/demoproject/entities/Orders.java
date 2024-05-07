package com.example.demoproject.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IDORDERS")
    private Integer idorders;

    @Column(name = "ORDERS_DATE")
    private java.sql.Timestamp ordersDate;

    @Column(name = "IDCUSTOMER")
    private Integer idcustomer;

    @Column(name = "TOTAL_MONEY")
    private Double totalMoney;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "NAME_RECIVER")
    private String nameReciver;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;


}
