package com.vn.devmaster.services.learnjpa.repository;

import com.vn.devmaster.services.learnjpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
