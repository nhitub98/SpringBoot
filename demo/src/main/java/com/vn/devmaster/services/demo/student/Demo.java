package com.vn.devmaster.services.demo.student;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add student");
            System.out.println("2. Edit student by id");
            System.out.println("3. Delete student by id");
            System.out.println("4. Sort student by gpa");
            System.out.println("5. Sort student by name");
            System.out.println("6. Show student");
            System.out.println("7.Exit");
            System.out.print("Nhap lua chon cua ban: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    student.add();
                    break;
                case 2:
                    student.edit();
                    break;
                case 3:
                    student.delete();
                    break;
                case 4:
                    student.sortByGpa();
                    break;
                case 5:
                    student.sortByName();
                    break;
                case 6:
                    student.display();
                    break;
                case 7:
                    System.out.println("Ket thuc chuong trinh");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        } while (choice != 7);
    }
}

