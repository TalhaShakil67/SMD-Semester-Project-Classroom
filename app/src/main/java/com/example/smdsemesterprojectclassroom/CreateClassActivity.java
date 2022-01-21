package com.example.smdsemesterprojectclassroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.Random;

public class CreateClassActivity extends AppCompatActivity {

    EditText classID, className;
    DbActions dbActions;
    TeacherModel loggedInTeacher;
    SharedPreferences teacherObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10)
        {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        dbActions = new DbActions();
        teacherObject = getSharedPreferences("LoggedInTeacher", 0);
        classID = findViewById(R.id.edittxtclassid);
        className = findViewById(R.id.edittxtclassname);
        classID.setText(salt.toString());

    }

    public void CreateClass(View view)
    {
        loggedInTeacher = new TeacherModel(
                Integer.parseInt(teacherObject.getString("id", "id not found")),
                teacherObject.getString("name", "name not found"),
                teacherObject.getString("email", "email not found"),
                teacherObject.getString("role", "role not found"),
                teacherObject.getString("qualification", "qualification not found"),
                Integer.parseInt(teacherObject.getString("age", "age not found")),
                Long.parseLong(teacherObject.getString("phoneNumber", "phone number not found"))
            );

        ClassroomModel classroom = new ClassroomModel(classID.getText().toString(), className.getText().toString(), loggedInTeacher);
        dbActions.createClassroom(classroom);
        Toast.makeText(getApplicationContext(), "Class Created Successfully", Toast.LENGTH_LONG).show();
        Log.d("teacher", classID.getText().toString() + className.getText().toString() + loggedInTeacher.getName());
    }
}