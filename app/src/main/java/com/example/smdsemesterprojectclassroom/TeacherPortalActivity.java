package com.example.smdsemesterprojectclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeacherPortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_portal);
    }

    public void CreateAClass(View view)
    {
        Intent intent = new Intent(this, CreateClassActivity.class);
        startActivity(intent);
    }
}