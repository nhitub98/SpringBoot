package com.vn.devmaster.service.demo5.demo5.repository;

import com.vn.devmaster.service.demo5.demo5.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value= "SELECT s FROM Student s WHERE s.address LIKE CONCAT('%',:address,'%') ")
    List<Student> findByAddress(@Param("address") String address);

    @Query(value= "SELECT * FROM student ORDER BY point DESC LIMIT 1", nativeQuery = true)
    Optional<Student> findHighestPoint();

    @Query(value= "SELECT s FROM Student s WHERE s.point > 5")
    List<Student> findPointGreaterThan5();


}
