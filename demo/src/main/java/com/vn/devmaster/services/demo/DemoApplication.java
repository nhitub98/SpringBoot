package com.vn.devmaster.services.demo;

import com.vn.devmaster.services.demo.dependency_demo.Employee;
import com.vn.devmaster.services.demo.sql_demo.Ketnoi;
import com.vn.devmaster.services.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
//      ConfigurableApplicationContext context=SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context= SpringApplication.run(DemoApplication.class, args);
//        Employee employee=context.getBean(Employee.class); //danh dau @Component o Employee truoc khi goi
//        employee.getPerson().wear();
        Ketnoi ketnoi= context.getBean(Ketnoi.class);
        ketnoi.getConnect().connect();
//          Student students= context.getBean(Student.class);
//          students.display();


    }

}
