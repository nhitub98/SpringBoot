package com.example.demo8.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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