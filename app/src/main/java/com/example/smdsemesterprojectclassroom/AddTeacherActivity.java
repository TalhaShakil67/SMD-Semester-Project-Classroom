package com.example.smdsemesterprojectclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class AddTeacherActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText name, email, phone, age, qualification, role;
    TeacherModel loggedInTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        dbActions = new DbActions();
        setLoggedInTeacher();

        name = findViewById(R.id.edittxtname);
        email = findViewById(R.id.edittxtemail);
        phone = findViewById(R.id.edittxtphone);
        age = findViewById(R.id.edittxtage);
        qualification = findViewById(R.id.edittxtqualification);
        role = findViewById(R.id.edittxtrole);

        name.setText("Tahir");
        email.setText("tahir.farooq@gmail.com");
        phone.setText("03007898043");
        age.setText("35");
        qualification.setText("Phd Computer Science");
        role.setText("Lecturer");

        /*name.setText("Hashim Yasin");
        email.setText("hashim.yasin@gmail.com");
        phone.setText("03007853884");
        age.setText("40");
        qualification.setText("Phd Computer Science");
        role.setText("Assistant Professor");*/

        /*Intent intent = new Intent();
        intent.putExtra("id", loggedInTeacher.getID());
        intent.putExtra("name", loggedInTeacher.getName());
        intent.putExtra("email", loggedInTeacher.getName());
        intent.putExtra("role", loggedInTeacher.getName());
        intent.putExtra("qualification", loggedInTeacher.getName());
        intent.putExtra("age", loggedInTeacher.getName());
        intent.putExtra("phoneNumber", loggedInTeacher.getName());
        setResult(101, intent);
        finish();
        Log.d("teacher", "activity closed and intented");*/

    }

    public void AddTeacher(View view) {
        Log.d("child", "getting id");
        Integer id = dbActions.getCurrentTeacherID();
        TeacherModel teacher = new TeacherModel(
                id,
                name.getText().toString(),
                email.getText().toString(),
                role.getText().toString(),
                qualification.getText().toString(),
                Integer.parseInt(age.getText().toString()),
                Long.parseLong(phone.getText().toString())
        );

        dbActions.addTeacher(teacher);
        Toast.makeText(getApplicationContext(), "Teacher Added Successfully", Toast.LENGTH_LONG).show();


    }

    public TeacherModel getLoggedInTeacher()
    {
        return loggedInTeacher;
    }


    public void setLoggedInTeacher() {
        Query query = dbActions.databaseReference.child("Teachers").orderByChild("name").equalTo("Hashim Yasin");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                TeacherModel dummy = null;
                Integer id = dbActions.getCurrentTeacherID();
                for (DataSnapshot teacher : snapshot.getChildren())
                {
                    dummy = new TeacherModel(
                            id,
                            teacher.child("name").getValue().toString(),
                            teacher.child("email").getValue().toString(),
                            teacher.child("role").getValue().toString(),
                            teacher.child("qualification").getValue().toString(),
                            Integer.parseInt(teacher.child("age").getValue().toString()),
                            Long.parseLong(teacher.child("phoneNumber").getValue().toString())
                    );
                    Log.d("teacher", "Dummy data: " + dummy.Qualification);

                    /*intent.putExtra("id", id);
                    intent.putExtra("name", teacher.child("name").getValue().toString());
                    intent.putExtra("email", teacher.child("email").getValue().toString());
                    intent.putExtra("role", teacher.child("role").getValue().toString());
                    intent.putExtra("qualification", teacher.child("qualification").getValue().toString());
                    intent.putExtra("age", Integer.parseInt(teacher.child("age").getValue().toString()));
                    intent.putExtra("phoneNumber", Long.parseLong(teacher.child("phoneNumber").getValue().toString()));
                    setResult(101, intent);
                    finish();
                    Log.d("teacher", "activity closed and intented");*/
                }
                loggedInTeacher = dummy;

                /*Intent intent = new Intent();
                intent.putExtra("LoggedInTeacher", getLoggedInTeacher());
                Log.d("teacher", "Added Mr. Hashim in intent " + getLoggedInTeacher().Qualification);
                setResult(101, intent);
                finish();
                Log.d("teacher", "activity closed");*/

                Intent intent = new Intent();
                intent.putExtra("id", dummy.getID());
                intent.putExtra("name", dummy.getName());
                intent.putExtra("email", dummy.getName());
                intent.putExtra("role", dummy.getName());
                intent.putExtra("qualification", dummy.getName());
                intent.putExtra("age", dummy.getName());
                intent.putExtra("phoneNumber", dummy.getName());
                setResult(101, intent);
                finish();
                Log.d("teacher", "activity closed and intented");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


}