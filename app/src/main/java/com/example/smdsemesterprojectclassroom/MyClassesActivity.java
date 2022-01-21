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

import java.util.ArrayList;

public class MyClassesActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText classCode;
    SharedPreferences studentObject;
    StudentModel loggedInStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);

        dbActions = new DbActions();
        classCode = findViewById(R.id.edittxtenterclasscode);
        studentObject = getSharedPreferences("LoggedInStudent", 0);

        //classCode.setText("HBXA2PJQ1F");
        classCode.setText("2XKN20T4S4");

        loggedInStudent = new StudentModel(
                Integer.parseInt(studentObject.getString("id", "id not found")),
                studentObject.getString("name", "name not found"),
                Long.parseLong(studentObject.getString("cnic", "cnic not found")),
                Integer.parseInt(studentObject.getString("age", "age not found")),
                Integer.parseInt(studentObject.getString("semester", "semester not found")),
                Float.parseFloat(studentObject.getString("cgpa", "cgpa not found"))
        );
    }

    public void JoinClass(View view)
    {
        Log.d("student", String.valueOf(loggedInStudent.getCNIC()));
        Query query = dbActions.databaseReference.child("Classrooms").orderByChild("code").equalTo(String.valueOf(classCode.getText()));
        Log.d("student", "querying");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("student", "gotcha");
                for (DataSnapshot reqClass : snapshot.getChildren())
                {
                    Log.d("student", "Class found " + reqClass.child("name").getValue().toString());
                    String classname = reqClass.child("name").getValue().toString();

                    /*ArrayList<StudentModel> std;
                    if(snap.hasChild("students"))
                    {
                        std = (ArrayList<StudentModel>) snap.child("students").getValue();
                        std.add(loggedInStudent);
                    }
                    else
                    {
                        std = new ArrayList<>();
                        std.add(loggedInStudent);
                    }
                    Log.d("student", String.valueOf(std.get(0).getName()));*/
                    //dbActions.databaseReference.child("Classrooms").child(classname).child("students").setValue(std);

                    Log.d("student", String.valueOf(loggedInStudent.getCNIC()));
                    /*for(StudentModel student : std)
                    {
                        dbActions.databaseReference.child("Classrooms").child(classname).child("students").child(student.getID().toString()).setValue(student);
                    }*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}