package com.example.smdsemesterprojectclassroom;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/*
public class TeacherModel implements Parcelable
{
    Integer ID;
    String Name, Email, Qualification, Role;
    Integer Age;
    Long PhoneNumber;

    public TeacherModel(Integer id, String name, String email, String role, String qualification, Integer age, Long phoneNumber) {
        ID = id;
        Name = name;
        Email = email;
        Qualification = qualification;
        Role = role;
        Age = age;
        PhoneNumber = phoneNumber;
    }

    protected TeacherModel(Parcel in) {
        if (in.readByte() == 0) {
            ID = null;
        } else {
            ID = in.readInt();
        }
        Name = in.readString();
        Email = in.readString();
        Qualification = in.readString();
        Role = in.readString();
        if (in.readByte() == 0) {
            Age = null;
        } else {
            Age = in.readInt();
        }
        if (in.readByte() == 0) {
            PhoneNumber = null;
        } else {
            PhoneNumber = in.readLong();
        }
    }

    public static final Creator<TeacherModel> CREATOR = new Creator<TeacherModel>() {
        @Override
        public TeacherModel createFromParcel(Parcel in) {
            return new TeacherModel(in);
        }

        @Override
        public TeacherModel[] newArray(int size) {
            return new TeacherModel[size];
        }
    };

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(Name);
        parcel.writeString(Email);
        parcel.writeString(Qualification);
        parcel.writeString(Role);
        parcel.writeInt(Age);
        parcel.writeLong(PhoneNumber);
    }
}
*/

public class TeacherModel
{
    Integer ID;
    String Name, Email, Qualification, Role;
    Integer Age;
    Long PhoneNumber;

    public TeacherModel(Integer ID, String name, String email, String role, String qualification, Integer age, Long phoneNumber) {
        this.ID = ID;
        Name = name;
        Email = email;
        Qualification = qualification;
        Role = role;
        Age = age;
        PhoneNumber = phoneNumber;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}