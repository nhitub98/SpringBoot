package com.vn.devmaster.services.demo.dependency_demo;

public class School {
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Person person;
    public School(Person person) {
        this.person = person;
    }



}
