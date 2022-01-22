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

public class LoginTeacherActivity extends AppCompatActivity {
    DbActions dbActions;
    EditText emailtxt;
    SharedPreferences teacherObject;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        dbActions = new DbActions();
        emailtxt = findViewById(R.id.edittxtlogincnic);
        teacherObject = getSharedPreferences("LoggedInTeacher", 0);
        editor = teacherObject.edit();
    }

    public void TeacherLogin(View view)
    {
        Query query = dbActions.databaseReference.child("Teachers").orderByChild("email").equalTo(emailtxt.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Integer id = dbActions.getCurrentTeacherID();
                for (DataSnapshot teacher : snapshot.getChildren())
                {
                    editor.putString("id", teacher.child("id").getValue().toString());
                    editor.putString("name", teacher.child("name").getValue().toString());
                    editor.putString("email", teacher.child("email").getValue().toString());
                    editor.putString("role", teacher.child("role").getValue().toString());
                    editor.putString("qualification", teacher.child("qualification").getValue().toString());
                    editor.putString("age", teacher.child("age").getValue().toString());
                    editor.putString("phoneNumber", teacher.child("phoneNumber").getValue().toString());
                    editor.commit();
                }
                Log.d("teacher", "added in shared preference");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
}