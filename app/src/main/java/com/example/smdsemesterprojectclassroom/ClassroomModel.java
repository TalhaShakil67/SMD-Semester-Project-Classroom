package com.example.smdsemesterprojectclassroom;

import com.example.smdsemesterprojectclassroom.StudentPortal.StudentModel;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherModel;

import java.util.ArrayList;

public class ClassroomModel
{
    String Code;
    String Name;
    TeacherModel Teacher;
    ArrayList<StudentModel> Students;

    public ClassroomModel(String code, String name, TeacherModel teacher)
    {
        Code = code;
        Name = name;
        Teacher = teacher;
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
