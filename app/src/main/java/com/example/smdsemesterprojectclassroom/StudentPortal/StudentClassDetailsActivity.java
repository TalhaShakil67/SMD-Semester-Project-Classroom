package com.example.smdsemesterprojectclassroom.StudentPortal;

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
import com.example.smdsemesterprojectclassroom.MyClassModelForAdapter;
import com.example.smdsemesterprojectclassroom.QuizModel;
import com.example.smdsemesterprojectclassroom.QuizTilesAdapter;
import com.example.smdsemesterprojectclassroom.R;
import com.example.smdsemesterprojectclassroom.StudentQuizActivity;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation.QuestionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentClassDetailsActivity extends AppCompatActivity {

    DbActions dbActions;
    StudentModel loggedInStudent;
    MyClassModelForAdapter myClass;
    ArrayList<QuizModel> quizesList;
    QuizTilesAdapter myAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView classTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_details);

        Intent intent = getIntent();
        loggedInStudent = (StudentModel) intent.getSerializableExtra("student");
        myClass = (MyClassModelForAdapter) intent.getSerializableExtra("class");

        setData();
        updateQuiz();

        myAdapter.setOnItemClickListener(new QuizTilesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent2 = new Intent(getApplicationContext(), StudentQuizActivity.class);
                intent2.putExtra("questions", quizesList.get(position).getQuestionsList());
                intent2.putExtra("quiztitle", quizesList.get(position).getQuizTitle());
                intent2.putExtra("quizscore", quizesList.get(position).getQuizScore());
                intent2.putExtra("student", loggedInStudent);
                intent2.putExtra("class", myClass);
                startActivity(intent2);
            }
        });

    }
    void updateQuiz()
    {
        Query query = dbActions.databaseReference.child("Classrooms").child(myClass.getName()).child("quizes");
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
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateQuiz();
    }

    void setData()
    {
        dbActions = new DbActions();

        classTitle = findViewById(R.id.student_classdetails_h1);

        classTitle.setText(myClass.getName());

        quizesList = new ArrayList<>();
        recyclerView = findViewById(R.id.student_classdetails_rec);
        myAdapter = new QuizTilesAdapter(quizesList);
        linearLayoutManager = new GridLayoutManager(this,2);//LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

    }
}