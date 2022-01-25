package com.example.smdsemesterprojectclassroom.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smdsemesterprojectclassroom.DbActions;
import com.example.smdsemesterprojectclassroom.R;
import com.example.smdsemesterprojectclassroom.StudentPortal.StudentModel;

public class AddStudentActivity extends AppCompatActivity {

    EditText nametxt, cnictxt, agetxt, semestertxt, cgpatxt, emailtxt, passtxt;
    Button submitButton;
    DbActions dbActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        dbActions = new DbActions();
        nametxt = findViewById(R.id.name_edit_txt);
        cnictxt = findViewById(R.id.cnic_edit_txt);
        agetxt = findViewById(R.id.age_edit_txt);
        semestertxt = findViewById(R.id.semester_edit_txt);
        cgpatxt = findViewById(R.id.cgpa_edit_txt);
        emailtxt = findViewById(R.id.editTextTextPersonName);
        passtxt = findViewById(R.id.editTextTextPersonName2);
        submitButton = findViewById(R.id.submit_btn);



        //For testing data
        /*nametxt.setText("Talha");
        cnictxt.setText("37406875972241");
        agetxt.setText("21");
        semestertxt.setText("7");
        cgpatxt.setText("3.2");
        emailtxt.setText("talha@123");
        passtxt.setText("123");*/

    }

    public void AddStudent(View view)
    {
        Integer id = dbActions.getCurrentStudentID();
        StudentModel student = new StudentModel(
                id,
                nametxt.getText().toString(),
                cnictxt.getText().toString(),
                Integer.parseInt(agetxt.getText().toString()),
                Integer.parseInt(semestertxt.getText().toString()),
                Float.parseFloat(cgpatxt.getText().toString()),
                emailtxt.getText().toString(),
                passtxt.getText().toString()
        );

        dbActions.addStudent(student);
        Toast.makeText(getApplicationContext(), "Student Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
}