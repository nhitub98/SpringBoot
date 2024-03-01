package com.example.demo2.bank;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Bank {
    private String bankName;
    private ArrayList<Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
    }

    public Bank(String bankName, ArrayList<Account> accounts) {
        this.bankName = bankName;
        this.accounts = accounts;
    }


    public void addAccount(Account account) {

        accounts.add(account);
    }

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank("Techcombank");
        Account acc1 = new Account(001, "Hien", 50000);
        bank.addAccount(acc1);
        acc1.napTien(50000);
        acc1.rutTien(30000);
        acc1.daoHan();
        double soTienChuyen = 10000;
        Account acc2 = new Account(002, "Hoa", 20000);
        bank.addAccount(acc2);
        System.out.println("Chuyen khoan " + soTienChuyen + " tu tai khoan " + acc1.getAccountNumber()+ " den tai khoan " + acc2.getAccountNumber());
        acc1.chuyenKhoan(acc2, soTienChuyen);
        acc1.hienThi();
        acc2.hienThi();
    }
}
