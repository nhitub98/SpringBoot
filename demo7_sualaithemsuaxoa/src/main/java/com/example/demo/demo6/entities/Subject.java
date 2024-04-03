package com.example.demo.demo6.entities;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;


    // Quan hệ nhiều-nhiều giữa Subject và Student
    // mappedBy: Xác định trường trong lớp Student định nghĩa quan hệ ManyToMany
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects") //subjects trùng tên trong List<subject> của Student
    //Lazy: khi .get thì mới được gọi câu lệnh sql
    //mapby là tên subject của student
    private List<Student> students= new ArrayList<>();



}
