package com.example.demo.demo6.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_subject")
public class StudentSubject {
    @Id
    @Column(name = "id_student")
    private Integer idStudent;

    @Id
    @Column(name = "id_subject")
    private Integer idSubject;

    @Column(name = "point")
    private Float point;


}
