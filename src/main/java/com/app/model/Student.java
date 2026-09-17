package com.app.model;

public class Student {
    private String regNo;
    private String name;

    public Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    public String getRegNo() { return regNo; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return regNo + " - " + name;
    }
}
