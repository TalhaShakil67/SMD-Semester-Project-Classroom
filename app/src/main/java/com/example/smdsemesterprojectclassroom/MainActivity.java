package com.example.smdsemesterprojectclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AddTeacher(View view)
    {
        intent = new Intent(this, AddTeacherActivity.class);
        startActivity(intent);
    }
    public void AddStudent(View view)
    {
        intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }
}