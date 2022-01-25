package com.example.smdsemesterprojectclassroom.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smdsemesterprojectclassroom.R;

public class AdminPortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal);
    }

    public void AddTeacher(View view)
    {
        Intent intent;
        intent = new Intent(this, AddTeacherActivity.class);
        startActivity(intent);
    }
    public void AddStudent(View view)
    {
        Intent intent;
        intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }
}