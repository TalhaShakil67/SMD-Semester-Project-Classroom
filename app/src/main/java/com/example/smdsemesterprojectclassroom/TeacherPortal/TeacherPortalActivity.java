package com.example.smdsemesterprojectclassroom.TeacherPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.smdsemesterprojectclassroom.DbActions;
import com.example.smdsemesterprojectclassroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherPortalActivity extends AppCompatActivity {

    DbActions dbActions;
    TextView teacherTitle;

    SharedPreferences teacherObject;

    TeacherModel loggedInTeacher;
    ArrayList<TeacherClassModel> teacherClassesList;
    TeacherClassRecAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_portal);

        dbActions = new DbActions();
        teacherTitle = findViewById(R.id.textView13);
        teacherClassesList = new ArrayList<>();
        teacherObject = getSharedPreferences("LoginDetailsObject", 0);

        recyclerView = findViewById(R.id.teacher_portal_rec);
        adapter = new TeacherClassRecAdapter(teacherClassesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        loggedInTeacher = new TeacherModel(
                Integer.parseInt(teacherObject.getString("id", "id not found")),
                teacherObject.getString("name", "name not found"),
                teacherObject.getString("email", "email not found"),
                teacherObject.getString("subject", "subject not found"),
                teacherObject.getString("qualification", "qualification not found"),
                Integer.parseInt(teacherObject.getString("age", "age not found")),
                Long.parseLong(teacherObject.getString("phoneNumber", "phoneNumber not found")),
                teacherObject.getString("password", "password not found")
        );

        teacherTitle.setText("Welcome " + loggedInTeacher.getName());

        //Retriveclasses
        Query query = dbActions.databaseReference.child("Classrooms");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                teacherClassesList.clear();
                Log.d("ok", "found "+snapshot.getChildrenCount());
                for(DataSnapshot classs : snapshot.getChildren())
                {

                    if (classs.child("teacher").child("email").getValue().toString().equals(loggedInTeacher.getEmail()))
                    {
                        teacherClassesList.add(new TeacherClassModel(
                                classs.child("code").getValue().toString(),
                                classs.child("name").getValue().toString(),
                                (int) classs.child("students").getChildrenCount())
                        );
                        Log.d("ok", "looping on classes");

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Move to create quiz
        adapter.setOnItemClickListener(new TeacherClassRecAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getApplicationContext(), TeacherClassDetailsActivity.class);
                intent.putExtra("class", teacherClassesList.get(position));
                intent.putExtra("teacher", loggedInTeacher);
                startActivity(intent);
                Log.d("found", teacherClassesList.get(position).getName());
            }
        });

    }

    public void CreateAClass(View view)
    {
        Intent intent = new Intent(this, CreateClassActivity.class);
        startActivity(intent);
    }

}