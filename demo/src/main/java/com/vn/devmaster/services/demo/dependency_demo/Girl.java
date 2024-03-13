package com.vn.devmaster.services.demo.dependency_demo;
import org.springframework.stereotype.Component;
@Component
public class Girl implements Person{
    @Override
    public void wear(){
        System.out.println("Girl wear Dress");
    }
    public void work(){
        System.out.println("Boy wear Jean");
    }
}
