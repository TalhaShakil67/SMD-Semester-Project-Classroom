package com.example.smdsemesterprojectclassroom.TeacherPortal;

import java.io.Serializable;

public class TeacherClassModel implements Serializable {

    String Code;
    String Name;
    int NumberOfStudents;

    public TeacherClassModel(String code, String name, int numberOfStudents) {
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

    public int getNumberOfStudents() {
        return NumberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        NumberOfStudents = numberOfStudents;
    }
}
