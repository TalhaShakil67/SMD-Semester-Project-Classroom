package com.example.smdsemesterprojectclassroom;

import java.util.ArrayList;

public class ClassroomModel
{
    String Code;
    String Name;
    TeacherModel Teacher;
    ArrayList<StudentModel> Students;

    public ClassroomModel(String code, String name, TeacherModel teacher, ArrayList<StudentModel> students) {
        Code = code;
        Name = name;
        Teacher = teacher;
        Students = students;
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

    public TeacherModel getTeacher() {
        return Teacher;
    }

    public void setTeacher(TeacherModel teacher) {
        Teacher = teacher;
    }

    public ArrayList<StudentModel> getStudents() {
        return Students;
    }

    public void setStudents(ArrayList<StudentModel> students) {
        Students = students;
    }
}
