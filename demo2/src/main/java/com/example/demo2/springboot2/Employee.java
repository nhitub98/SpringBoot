package com.example.demo2.springboot2;

import org.springframework.stereotype.Component;

@Component
public class Employee implements IPerson{
    @Override
    public void wear() {
        System.out.println("Nhan vien mac dong phuc");
    }

    @Override
    public void work() {
        System.out.println("Nhan vien lam viec");
    }
}
