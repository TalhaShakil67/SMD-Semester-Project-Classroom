package com.example.smdsemesterprojectclassroom.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smdsemesterprojectclassroom.DbActions;
import com.example.smdsemesterprojectclassroom.R;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddTeacherActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText name, email, phone, age, qualification, subject, password;
    TeacherModel loggedInTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        dbActions = new DbActions();
        //setLoggedInTeacher();

        name = findViewById(R.id.edittxtname);
        email = findViewById(R.id.edittxtemail);
        phone = findViewById(R.id.edittxtphone);
        age = findViewById(R.id.edittxtage);
        qualification = findViewById(R.id.edittxtqualification);
        subject = findViewById(R.id.edittxtrole);
        password = findViewById(R.id.add_teacher_edt6);


        //For testing data
        /*name.setText("Tahir Farooq");
        email.setText("tahir.farooq@gmail.com");
        phone.setText("03007853884");
        age.setText("31");
        qualification.setText("Phd Computer Science");
        subject.setText("Professor");
        password.setText("123");*/

    }

    public void AddTeacher(View view) {
        Log.d("child", "getting id");
        Integer id = dbActions.getCurrentTeacherID();
        TeacherModel teacher = new TeacherModel(
                id,
                name.getText().toString(),
                email.getText().toString(),
                subject.getText().toString(),
                qualification.getText().toString(),
                Integer.parseInt(age.getText().toString()),
                Long.parseLong(phone.getText().toString()),
                password.getText().toString()
        );

        dbActions.addTeacher(teacher);
        Toast.makeText(getApplicationContext(), "Teacher Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }




    ///Faltu kam
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
                //Intent intent = new Intent();
                Integer id = dbActions.getCurrentTeacherID();
                for (DataSnapshot teacher : snapshot.getChildren())
                {
                    Log.d("teacher", "intent added");

                }
                loggedInTeacher = dummy;

                Log.d("teacher", "activity closed and intented");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


}