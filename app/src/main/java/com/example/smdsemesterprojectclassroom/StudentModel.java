package com.example.smdsemesterprojectclassroom;

public class StudentModel
{
    private Integer ID, Age, Semester;
    private String Name;
    private long CNIC;
    private float CGPA;

    public StudentModel(Integer ID, String name, long CNIC, Integer age, Integer semester, float CGPA)
    {
        this.ID = ID;
        this.Name = name;
        this.CNIC = CNIC;
        this.Age = age;
        this.Semester = semester;
        this.CGPA = CGPA;
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

    public long getCNIC() {
        return CNIC;
    }

    public void setCNIC(long CNIC) {
        this.CNIC = CNIC;
    }

    public float getCGPA() {
        return CGPA;
    }

    public void setCGPA(float CGPA) {
        this.CGPA = CGPA;
    }
}
