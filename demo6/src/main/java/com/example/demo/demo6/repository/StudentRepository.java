package com.example.demo.demo6.repository;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value= "SELECT s FROM Student s WHERE s.address LIKE CONCAT('%',:address,'%') ")
    List<Student> findByAddress(@Param("address") String address);

    @Query(value = "SELECT s FROM Student s WHERE s.point = (SELECT MAX(s2.point) FROM Student s2)")
    List<Student> findHighestPoint();

    @Query(value= "SELECT s FROM Student s WHERE s.point > 5")
    List<Student> findPointGreaterThan5();


}

