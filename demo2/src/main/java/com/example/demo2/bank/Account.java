package com.example.demo2.bank;

import lombok.Data;

import java.text.DecimalFormat;

@Data
public class Account {
    private final double laiSuat = 0.035;
    private final double phiRutTien = 0.5;
    private long accountNumber;

    public double getLaiSuat() {
        return laiSuat;
    }

    public double getPhiRutTien() {
        return phiRutTien;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private String accountName;
    private double balance;

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                '}';
    }
    public Account(long accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }
    public Account() {}
    public Account(long accountNumber,  double soDuBanDau) {
        this.accountNumber = accountNumber;
        this.balance = soDuBanDau;
    }
    //    public Account(long accountNumber) {
//        this(accountNumber, 50000);
//    }
    public void napTien(double tienNap) {
        if (tienNap > 0) {
            balance += tienNap;
            System.out.println("So du hien tai cua tai khoan "+this.getAccountNumber()+ " la " + dinhDangTienTe(balance));
        } else {
            System.out.println("So tien nap khong hop le");
        }
    }
    public double rutTien(double tienRut) {
        double tongTienRut = tienRut + phiRutTien;
        if (tienRut > balance) {
            System.out.println("So tien chuyen không hop le hoac khong du tien trong tai khoan");
            return 0;
        } else {
            balance -= tienRut;
            System.out.println("So du hien tai cua tai khoan "+this.getAccountNumber()+ " la " + dinhDangTienTe(balance));
            return tienRut;
        }
    }
    private String dinhDangTienTe(double tien) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return "$" + formatter.format(tien);
    }
    public void daoHan() {
        double tienLai = balance + (balance * laiSuat);
        balance += tienLai;
        System.out.println("So tien sau khi dao han: " + dinhDangTienTe(balance));
    }
    public void hienThi() {
        System.out.println(this);
    }
    public void chuyenKhoan(Account nguoiNhan, double tienChuyen) {
        if (tienChuyen <= balance) {
            rutTien(tienChuyen);
            nguoiNhan.napTien(tienChuyen);
            System.out.println("Chuyen khoan thanh cong. So du hien tai cua tai khoan: "+this.getAccountNumber()+ " la " + dinhDangTienTe(balance));
        } else {
            System.out.println("So tien chuyen khoan khong hop le hoac khong du tien trong tai khoan");
        }
    }

}
