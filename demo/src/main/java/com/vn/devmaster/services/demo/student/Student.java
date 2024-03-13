package com.vn.devmaster.services.demo.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Student {
    private static List<Student> students = new ArrayList<>();
    private String id;
    private String name;
    private int age;
    private String address;
    private float gpa;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", gpa=" + gpa +
                '}';
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.add();
        student.display();
        student.sortByGpa();
        student.sortByName();
        student.delete();
        student.edit();
    }

    void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so sinh vien: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap sinh vien thu " + (i + 1) + ":");
            System.out.print("Nhap ID: ");
            String id = scanner.next();
            System.out.print("Nhap ten: ");
            String name = scanner.next();
            System.out.print("Nhap tuoi: ");
            int age = scanner.nextInt();
            System.out.print("Nhap dia chi: ");
            String address = scanner.next();
            System.out.print("Enter GPA: ");
            float gpa = scanner.nextFloat();
            students.add(new Student(id, name, age, address, gpa));
        }
    }

    public void display() {
        System.out.println("Hien thi danh sach sinh vien: ");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    void sortByGpa() {
        System.out.println("Danh sach diem giam dan theo GPA:");
        Collections.sort(students, (s1, s2) -> Float.compare(s2.getGpa(), s1.getGpa()));
        for (Student student : students) {
            System.out.println(student);
        }
    }

    void sortByName() {
        System.out.println("Danh sach ten giam dan theo ten");
        Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));
        for (Student student : students) {
            System.out.println(student);
        }
    }

    void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ID sinh vien can xoa: ");
        String id = scanner.next();
        students.removeIf(student -> student.getId().equals(id));
    }

    void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ID sinh vien can sua: ");
        String id = scanner.next();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                System.out.print("Nhap ten moi: ");
                student.setName(scanner.next());
                System.out.print("Nhap tuoi moi: ");
                student.setAge(scanner.nextInt());
                System.out.print("Dia chi moi: ");
                student.setAddress(scanner.next());
                System.out.print("GPA moi: ");
                student.setGpa(scanner.nextFloat());
                return;
            }
        }
        System.out.println("Khong tim thay sinh vien");
    }
}
