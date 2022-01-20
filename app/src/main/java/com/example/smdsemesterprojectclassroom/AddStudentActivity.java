package com.example.smdsemesterprojectclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    EditText nametxt, cnictxt, agetxt, semestertxt, cgpatxt;
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
        submitButton = findViewById(R.id.submit_btn);

        nametxt.setText("Talha");
        cnictxt.setText("37406875972241");
        agetxt.setText("21");
        semestertxt.setText("7");
        cgpatxt.setText("3.2");

        /*nametxt.setText("Zaid");
        cnictxt.setText("3740646811141");
        agetxt.setText("20");
        semestertxt.setText("5");
        cgpatxt.setText("2.8");*/
    }

    public void AddStudent(View view)
    {
        Integer id = dbActions.getCurrentStudentID();
        StudentModel student = new StudentModel(
                id,
                nametxt.getText().toString(),
                Long.parseLong(cnictxt.getText().toString()),
                Integer.parseInt(agetxt.getText().toString()),
                Integer.parseInt(semestertxt.getText().toString()),
                Float.parseFloat(cgpatxt.getText().toString())
        );

        dbActions.addStudent(student);
        Toast.makeText(getApplicationContext(), "Student Added Successfully", Toast.LENGTH_LONG).show();
    }
}