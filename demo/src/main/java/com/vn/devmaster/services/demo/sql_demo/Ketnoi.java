package com.vn.devmaster.services.demo.sql_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ketnoi {
    @Autowired //annotion viet ngay tren cai minh muon dung
    private ISql ketnoi;
    public Ketnoi(ISql connect) {
        this.ketnoi = connect;
    }
    public ISql getConnect() {
        return ketnoi;
    }

    public void setConnect(ISql connect) {
        this.ketnoi = connect;
    }

}
