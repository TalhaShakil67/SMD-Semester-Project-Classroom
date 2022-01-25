package com.example.smdsemesterprojectclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smdsemesterprojectclassroom.AdminPortal.AddStudentActivity;
import com.example.smdsemesterprojectclassroom.AdminPortal.AddTeacherActivity;
import com.example.smdsemesterprojectclassroom.LoggedOutRouting.LoggedOutActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoggedOutActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, 900);

    }


}