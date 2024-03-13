package com.vn.devmaster.services.demo.dependency_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class Employee {
@Qualifier("girl") //Ưu tiên chọn Primary, nếu không có Primary sẽ chọn Qualifier
//khi person có boy và girl đánh complement đều trỏ đến person
    @Autowired
    private Person person; //khai bao bien person
//    public Employee(Person person) {
//        this.person = person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }
//
    public Person getPerson() {
        return person;
    }
}
//
