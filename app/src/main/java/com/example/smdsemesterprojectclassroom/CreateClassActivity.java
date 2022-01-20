package com.example.smdsemesterprojectclassroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class CreateClassActivity extends AppCompatActivity {

    EditText classID, className;
    DbActions dbActions;
    TeacherModel loggedInTeacher;
    ArrayList<StudentModel> studentsEnrolled;
    SharedPreferences teacherObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) // length of the random string.
        {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        dbActions = new DbActions();
        teacherObject = getSharedPreferences("LoggedInTeacher", 0);
        studentsEnrolled = new ArrayList<>();
        classID = findViewById(R.id.edittxtclassid);
        className = findViewById(R.id.edittxtclassname);
        classID.setText(salt.toString());

    }

    public void CreateClass(View view)
    {
        // intent=new Intent(this,AddTeacherActivity.class);
        //startActivityForResult(intent, 101);// Activity is started with requestCode 101


        loggedInTeacher = new TeacherModel(
                Integer.parseInt(teacherObject.getString("id", "id not found")),
                teacherObject.getString("name", "name not found"),
                teacherObject.getString("email", "email not found"),
                teacherObject.getString("role", "role not found"),
                teacherObject.getString("qualification", "qualification not found"),
                Integer.parseInt(teacherObject.getString("age", "age not found")),
                Long.parseLong(teacherObject.getString("phoneNumber", "phone number not found"))
            );

        Log.d("teacher", loggedInTeacher.Qualification);
        //ClassroomModel classroom = new ClassroomModel(classID.getText().toString(), className.getText().toString(), loggedInTeacher, studentsEnrolled);
        //dbActions.createClassroom(classroom);
        //Toast.makeText(getApplicationContext(), "Class Created Successfully", Toast.LENGTH_LONG).show();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 101
        if(requestCode == 101)
        {
            Log.d("teacher", data.getExtras().getString("id").toString());
            //loggedInTeacher = data.getParcelableExtra("LoggedInTeacher");

            *//*loggedInTeacher = new TeacherModel(

                    Integer.parseInt(data.getExtras().getString("id")),
                    data.getExtras().getString("name"),
                    data.getExtras().getString("email"),
                    data.getExtras().getString("role"),
                    data.getExtras().getString("qualification"),
                    Integer.parseInt(data.getExtras().getString("age")),
                    Long.parseLong(data.getExtras().getString("phoneNumber"))
            );

            Log.d("teacher", loggedInTeacher.Qualification);*//*
        }
    }*/
}