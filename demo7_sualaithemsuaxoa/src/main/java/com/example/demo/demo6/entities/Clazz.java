package com.example.demo.demo6.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "clazz")
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

    @OneToMany(mappedBy = "clazz")
    private List<Student> students;

}
