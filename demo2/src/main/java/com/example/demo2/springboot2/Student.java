package com.example.demo2.springboot2;

import org.springframework.boot.ssl.pem.PemSslStore;
import org.springframework.stereotype.Component;

@Component
public class Student implements IPerson {
    @Override
    public void wear() {
        System.out.println("Hoc sinh mac dong phuc");
    }

    @Override
    public void work() {
        System.out.println("Hoc sinh lam viec");
    }
}
