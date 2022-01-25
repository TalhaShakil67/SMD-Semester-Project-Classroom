package com.example.smdsemesterprojectclassroom.TeacherPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smdsemesterprojectclassroom.DbActions;
import com.example.smdsemesterprojectclassroom.QuizTilesAdapter;
import com.example.smdsemesterprojectclassroom.R;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation.CreateQuizActivity;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation.QuestionModel;
import com.example.smdsemesterprojectclassroom.QuizModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherClassDetailsActivity extends AppCompatActivity {

    DbActions dbActions;
    TeacherModel loggedInTeacher;
    TeacherClassModel classSelected;
    ArrayList<QuizModel> quizesList;
    QuizTilesAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView title,classCode,stdCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_details);

        Intent intent = getIntent();
        loggedInTeacher = (TeacherModel) intent.getSerializableExtra("teacher");
        classSelected = (TeacherClassModel) intent.getSerializableExtra("class");

        setData();

        updateQuiz();

    }
    void updateQuiz()
    {
        Query query = dbActions.databaseReference.child("Classrooms").child(classSelected.getName()).child("quizes");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quizesList.clear();
                for(DataSnapshot quizes : snapshot.getChildren())
                {
                    ArrayList<QuestionModel>  questions = new ArrayList<>();
                    for (DataSnapshot quiz : quizes.getChildren())
                    {
                        if (!quiz.getKey().toString().equals("score") && !quiz.getKey().toString().equals("title"))
                        {
                            questions.add(new QuestionModel(
                                    quiz.child("question").getValue().toString(),
                                    quiz.child("option1").getValue().toString(),
                                    quiz.child("option2").getValue().toString(),
                                    quiz.child("option3").getValue().toString(),
                                    quiz.child("option4").getValue().toString(),
                                    Integer.parseInt(quiz.child("correct").getValue().toString()),
                                    Integer.parseInt(quiz.child("marks").getValue().toString())
                            ));
                        }
                    }
                    quizesList.add(new QuizModel(quizes.child("title").getValue().toString(),
                            Integer.parseInt(quizes.child("score").getValue().toString()),
                            questions
                    ));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    void setData()
    {
        dbActions = new DbActions();

        title = findViewById(R.id.teacher_classdetails_h1);
        classCode = findViewById(R.id.teacher_classdetails_code);
        stdCount = findViewById(R.id.teacher_classdetails_stdcount);

        title.setText(classSelected.getName());
        classCode.setText("Classroom Code: " + classSelected.getCode());
        stdCount.setText("Total Student: " + String.valueOf(classSelected.NumberOfStudents));

        quizesList = new ArrayList<>();
        recyclerView = findViewById(R.id.teacher_classdetails_rec);
        adapter = new QuizTilesAdapter(quizesList);
        linearLayoutManager = new GridLayoutManager(this,2);//LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        updateQuiz();
    }

    public void MakeQuiz(View view) {
        Intent intent = new Intent(this, CreateQuizActivity.class);
        intent.putExtra("teacher", loggedInTeacher);
        intent.putExtra("class", classSelected);
        startActivity(intent);
    }
}