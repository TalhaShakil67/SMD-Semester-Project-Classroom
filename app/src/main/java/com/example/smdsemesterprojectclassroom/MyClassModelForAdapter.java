package com.example.smdsemesterprojectclassroom;

import java.util.ArrayList;

public class MyClassModelForAdapter
{
    String Code;
    String Name;
    Integer NumberOfStudents;

    public MyClassModelForAdapter(String code, String name, Integer numberOfStudents) {
        Code = code;
        Name = name;
        NumberOfStudents = numberOfStudents;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getNumberOfStudents() {
        return NumberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        NumberOfStudents = numberOfStudents;
    }
}
