package com.example.demoproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

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


//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "ORDERS_DATE")
    private LocalDateTime ordersDate;

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

    @Column(name = "STATUS")
    private int status;

}
