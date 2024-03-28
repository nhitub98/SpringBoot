package com.example.demo.demo6.repository;

import com.example.demo.demo6.entities.Clazz;
import com.example.demo.demo6.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
