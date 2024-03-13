package com.vn.devmaster.services.demo.sql_demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MySQL implements ISql{
    @Override
    public void connect(){
        System.out.println("Connect to MySQL");
    }
}
