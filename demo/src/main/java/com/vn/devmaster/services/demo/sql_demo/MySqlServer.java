package com.vn.devmaster.services.demo.sql_demo;

import org.springframework.stereotype.Component;

@Component
public class MySqlServer implements ISql {
    @Override
    public void connect(){
        System.out.println("Connect to MySQLServer");
    }
}
