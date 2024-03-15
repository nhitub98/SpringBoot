package com.vn.devmaster.services.learnjpa.repository;

import com.vn.devmaster.services.learnjpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    //Cách 1: HQL
    @Query(value= "select s from Student s where s.address like concat('%',:address,'%')")
    List<Student> findByAddress(@Param("address") String address);
    //Cách 2:
    @Query(value="select * from Student where address like concat('%') ")
}
