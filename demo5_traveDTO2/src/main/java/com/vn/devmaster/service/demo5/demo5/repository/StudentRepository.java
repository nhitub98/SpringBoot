package com.vn.devmaster.service.demo5.demo5.repository;

import com.vn.devmaster.service.demo5.demo5.dto.StudentDTO;
import com.vn.devmaster.service.demo5.demo5.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value= "SELECT s FROM Student s WHERE s.address LIKE CONCAT('%',:address,'%') ")
    List<Student> findByAddress(@Param("address") String address);

    @Query(value = "SELECT s FROM Student s WHERE s.point = (SELECT MAX(s2.point) FROM Student s2)")
    List<Student> findHighestPoint();

    @Query(value= "SELECT s FROM Student s WHERE s.point > 5")
    List<StudentDTO> findPointGreaterThan5();


}
