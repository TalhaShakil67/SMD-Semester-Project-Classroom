package com.example.smdsemesterprojectclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyClassesActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText classCode;
    SharedPreferences studentObject;
    StudentModel loggedInStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);

        dbActions = new DbActions();
        classCode = findViewById(R.id.edittxtenterclasscode);
        studentObject = getSharedPreferences("LoggedInStudent", 0);

        classCode.setText("C7ZD01WKY7");        //testing

        loggedInStudent = new StudentModel(
                Integer.parseInt(studentObject.getString("id", "id not found")),
                studentObject.getString("name", "name not found"),
                Long.parseLong(studentObject.getString("cnic", "cnic not found")),
                Integer.parseInt(studentObject.getString("age", "age not found")),
                Integer.parseInt(studentObject.getString("semester", "semester not found")),
                Float.parseFloat(studentObject.getString("cgpa", "cgpa not found"))
        );
    }

    public void JoinClass(View view)
    {
        Query query = dbActions.databaseReference.child("Classrooms").orderByChild("code").equalTo(String.valueOf(classCode.getText()));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot reqClass : snapshot.getChildren())
                {
                    String classname = reqClass.child("name").getValue().toString();
                    dbActions.databaseReference.child("Classrooms").child(classname).child("students").child("Student ID "+loggedInStudent.getID()).setValue(loggedInStudent);
                    Toast.makeText(getApplicationContext(), "Class Joined Successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}