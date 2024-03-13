package com.vn.devmaster.services.demo.config;

import com.vn.devmaster.services.demo.student.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DemoConfig {
    @Bean
    Student defaultStudent() {
        return Student.builder()
                .id("23")
                .name("hien")
                .age(20)
                .address("address")
                .gpa(4)
                .build();
    }
}
