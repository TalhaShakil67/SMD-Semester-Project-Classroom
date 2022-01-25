package com.example.smdsemesterprojectclassroom.StudentPortal;

import java.io.Serializable;

public class StudentModel implements Serializable
{
    private Integer ID, Age, Semester;
    private String Name;
    private String CNIC;
    private float CGPA;
    private String Email;
    private String Password;



    public StudentModel(Integer ID, String name, String CNIC, Integer age, Integer semester, float CGPA, String email, String password)
    {
        this.ID = ID;
        this.Name = name;
        this.CNIC = CNIC;
        this.Age = age;
        this.Semester = semester;
        this.CGPA = CGPA;
        this.Email = email;
        this.Password = password;
    }

    public Integer getID(){
        return ID;
    }

    public void setID(Integer id)
    {
        ID = id;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Integer getSemester() {
        return Semester;
    }

    public void setSemester(Integer semester) {
        Semester = semester;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public float getCGPA() {
        return CGPA;
    }

    public void setCGPA(float CGPA) {
        this.CGPA = CGPA;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
