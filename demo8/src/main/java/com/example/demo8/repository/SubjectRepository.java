package com.example.demo8.repository;

import com.example.demo8.entities.Clazz;
import com.example.demo8.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
