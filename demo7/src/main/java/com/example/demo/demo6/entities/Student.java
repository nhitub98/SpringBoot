package com.example.demo.demo6.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;
    @Column(name = "point")
    private double point;
//    @ManyToOne(fetch = FetchType.EAGER)

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) // Quan hệ nhiều-một giữa Student và Clazz
    @JoinColumn(name = "clazz_id") //// Khóa ngoại tham chiếu đến bảng Clazz
    private Clazz clazz;


    @ManyToMany   // Quan hệ nhiều-nhiều giữa Student và Subject
    @JoinTable(name = "student_subject", // Tên bảng liên kết
    joinColumns = @JoinColumn(name = "id_student",referencedColumnName = "id"), // Khóa ngoại tham chiếu đến bảng Student
    inverseJoinColumns = @JoinColumn(name = "id_subject",referencedColumnName = "id")) //// Khóa ngoại tham chiếu đến bảng Subject
    private List<Subject> subjects= new ArrayList<>(); //trong student có nhiều môn học



}
