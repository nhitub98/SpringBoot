package com.example.demo.demo6.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Table(name = "clazz")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clazz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
