package com.example.smdsemesterprojectclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginStudentActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText cnictxt;
    SharedPreferences studentObject;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        dbActions = new DbActions();
        cnictxt = findViewById(R.id.edittxtlogincnic);
        studentObject = getSharedPreferences("LoggedInStudent", 0);
        editor = studentObject.edit();

        cnictxt.setText("37406875972241");
    }

    public void StudentLogin(View view)
    {
        Query query = dbActions.databaseReference.child("Students").orderByChild("cnic").equalTo(Long.parseLong(cnictxt.getText().toString()));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Integer id = dbActions.getCurrentTeacherID();
                for (DataSnapshot std : snapshot.getChildren())
                {
                    editor.putString("id", std.child("id").getValue().toString());
                    editor.putString("name", std.child("name").getValue().toString());
                    editor.putString("cnic", std.child("cnic").getValue().toString());
                    editor.putString("age", std.child("age").getValue().toString());
                    editor.putString("semester", std.child("semester").getValue().toString());
                    editor.putString("cgpa", std.child("cgpa").getValue().toString());
                    editor.commit();
                    Log.d("student", "added in shared preference");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
}