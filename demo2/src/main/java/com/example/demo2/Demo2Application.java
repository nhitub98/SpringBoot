package com.example.demo2;

import com.example.demo2.springboot2.Company;
import com.example.demo2.springboot2.Employee;
import com.example.demo2.springboot2.School;
import com.example.demo2.springboot2.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(Demo2Application.class, args);
//        Employee employee= context.getBean(Employee.class);
//        employee.wear();
//        Company company= context.getBean(Company.class);
//        company.getEmployeeDependence().work();
//        Student student= context.getBean(Student.class);
//        student.work();
//        School school= context.getBean(School.class);
//        school.getStudentDependence().wear();
//        Student student1= context.getBean(Student.class);


    }

}
