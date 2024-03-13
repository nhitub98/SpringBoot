package com.vn.devmaster.services.demo.dependency_demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component //có thể đặt tên cho @Component("") và gọi ở Employee
@Primary
public class Boy implements Person{
    @Override
    public void wear(){
        System.out.println("Boy wear Jean");
    }
    public void work(){
        System.out.println("Boy wear Jean");
    }
}
