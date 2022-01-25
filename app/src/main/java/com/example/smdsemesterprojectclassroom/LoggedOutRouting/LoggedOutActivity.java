package com.example.smdsemesterprojectclassroom.LoggedOutRouting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smdsemesterprojectclassroom.AdminPortal.AdminPortalActivity;
import com.example.smdsemesterprojectclassroom.DbActions;
import com.example.smdsemesterprojectclassroom.StudentPortal.MyClassesActivity;
import com.example.smdsemesterprojectclassroom.R;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherPortalActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoggedOutActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText username_edt;
    EditText password_edt;

    SharedPreferences loginDetailsObject;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_out);

        dbActions = new DbActions();
        username_edt = findViewById(R.id.loggedoutedittxt_uname);
        password_edt = findViewById(R.id.loggedoutedittxt_pass);

        loginDetailsObject = getSharedPreferences("LoginDetailsObject", 0);
        editor = loginDetailsObject.edit();
        editor.clear();

        //Testing
        //username_edt.setText("admin");
        //password_edt.setText("admin");
        //Testing
        //username_edt.setText("talha@123");
        //password_edt.setText("123");
        //Testing
        username_edt.setText("hashim@123");
        password_edt.setText("123");
    }

    public void LoginBtn(View view) {
        String username = username_edt.getText().toString();
        String password = password_edt.getText().toString();

        if (username.length() == 0)
        {
            Toast.makeText(this, "Please Enter user email", Toast.LENGTH_SHORT).show();
        }
        else if (password.length() == 0)
        {
            Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            final int[] accountType = {0}; // 1=student,2=teachers,3=admin

            //Student
            Query query = dbActions.databaseReference.child("Students").orderByChild("email").equalTo(username);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() !=0)
                    {
                        for (DataSnapshot std : snapshot.getChildren())
                        {
                            if (std.child("email").getValue().toString().equals(username)
                                    && std.child("password").getValue().toString().equals(password))
                            {
                                editor.putString("id", std.child("id").getValue().toString());
                                editor.putString("name", std.child("name").getValue().toString());
                                editor.putString("cnic", std.child("cnic").getValue().toString());
                                editor.putString("age", std.child("age").getValue().toString());
                                editor.putString("semester", std.child("semester").getValue().toString());
                                editor.putString("cgpa", std.child("cgpa").getValue().toString());
                                editor.putString("email", std.child("email").getValue().toString());
                                editor.putString("password", std.child("password").getValue().toString());
                                editor.commit();

                                Log.d("found", "Student Login uname:" + std.child("name").getValue().toString()
                                        +" | pass:"+std.child("cnic").getValue().toString());
                                RouteToStudent();
                            }
                        }
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //Teacher
            query = dbActions.databaseReference.child("Teachers").orderByChild("email").equalTo(username);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() !=0)
                    {
                        for (DataSnapshot std : snapshot.getChildren())
                        {
                            if (std.child("email").getValue().toString().equals(username)
                            && std.child("password").getValue().toString().equals(password))
                            {
                                editor.putString("id", std.child("id").getValue().toString());
                                editor.putString("name", std.child("name").getValue().toString());
                                editor.putString("email", std.child("email").getValue().toString());
                                editor.putString("subject", std.child("subject").getValue().toString());
                                editor.putString("qualification", std.child("qualification").getValue().toString());
                                editor.putString("age", std.child("age").getValue().toString());
                                editor.putString("phoneNumber", std.child("phoneNumber").getValue().toString());
                                editor.putString("password", std.child("password").getValue().toString());
                                editor.commit();
                                Log.d("found", "Teacher Login uname:" + std.child("email").getValue().toString()
                                        +" | pass:"+std.child("name").getValue().toString());
                                RouteToTeacher();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //Admin
            query = dbActions.databaseReference.child("Admins").orderByChild("email").equalTo(username);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() !=0)
                    {
                        for (DataSnapshot std : snapshot.getChildren())
                        {
                            if (std.child("email").getValue().toString().equals(username)
                                    && std.child("password").getValue().toString().equals(password))
                            {
                                editor.putString("email", std.child("email").getValue().toString());
                                editor.putString("name", std.child("name").getValue().toString());
                                editor.putString("password", std.child("password").getValue().toString());
                                editor.putString("role", "admin");
                                editor.commit();

                                Log.d("found", "Admin Login uname:" + std.child("email").getValue().toString()
                                        +" | pass:"+std.child("password").getValue().toString());
                                RouteToAdmin();
                            }
                        }
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }

    private void RouteToTeacher()
    {
        Intent intent = new Intent(this, TeacherPortalActivity.class);
        startActivity(intent);
        finish();
    }
    private void RouteToStudent()
    {
        Intent intent = new Intent(this, MyClassesActivity.class);
        startActivity(intent);
        finish();
    }
    private void RouteToAdmin()
    {
        Intent intent = new Intent(this, AdminPortalActivity.class);
        startActivity(intent);
        finish();
    }
}