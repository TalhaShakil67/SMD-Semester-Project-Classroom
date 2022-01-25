package com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smdsemesterprojectclassroom.DbActions;
import com.example.smdsemesterprojectclassroom.R;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherClassModel;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateQuizActivity extends AppCompatActivity {

    DbActions dbActions;
    CreateQuizRecAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<QuestionModel> questionList;
    LinearLayoutManager linearLayoutManager;

    TextView textView;
    String q_count = "Total Quiz Question: ";
    TeacherModel loggedInTeacher;
    TeacherClassModel classSelected;
    EditText quizTitle;
    EditText quizScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        Intent intent = getIntent();
        loggedInTeacher = (TeacherModel) intent.getSerializableExtra("teacher");
        classSelected = (TeacherClassModel) intent.getSerializableExtra("class");

        setData();


    }

    void setData()
    {
        dbActions = new DbActions();

        questionList = new ArrayList<QuestionModel>();

        textView = findViewById(R.id.create_quiz_h2);
        textView.setText(q_count + String.valueOf(questionList.size()));

        quizTitle = findViewById(R.id.create_quiz_title_edttxt);
        quizScore = findViewById(R.id.create_quiz_score_edttxt);

        recyclerView = findViewById(R.id.create_quiz_rec);
        adapter = new CreateQuizRecAdapter(questionList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void AddQuestion(View view) {
        QuestionModel model = new QuestionModel("", "","", "", "", 0, 0);
        questionList.add(0,model);
        adapter.notifyDataSetChanged();
        textView.setText(q_count + String.valueOf(questionList.size()));
    }

    public void SubmitQuiz(View view) {
        ArrayList<QuestionModel> list = adapter.getArrayList();
        String title = quizTitle.getText().toString();
        String score = quizScore.getText().toString();

        if (title.length() == 0)
        {
            Toast.makeText(this, "Please Enter quiz title!", Toast.LENGTH_SHORT).show();
        }
        else if (score.length() == 0)
        {
            Toast.makeText(this, "Please Enter quiz total score!", Toast.LENGTH_SHORT).show();
        }
        else if (list.size() > 0)
        {
            Query query = dbActions.databaseReference.child("Classrooms").child(classSelected.getName());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int quizCount = 0;
                    if(snapshot.hasChild("quizes"))
                    {
                        quizCount = (int) snapshot.child("quizes").getChildrenCount();
                    }
                    quizCount = quizCount + 1;
                    dbActions.databaseReference.child("Classrooms").child(classSelected.getName())
                            .child("quizes").child("quiz" + String.valueOf(quizCount)).child("title").setValue(title);
                    dbActions.databaseReference.child("Classrooms").child(classSelected.getName())
                            .child("quizes").child("quiz" + String.valueOf(quizCount)).child("score").setValue(Integer.valueOf(score));
                    for (int i=0;i< list.size(); i++)
                    {
                        if (list.get(i).getQuiz_question().length() > 0)
                        {
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("correct").setValue(Integer.valueOf(list.get(i).correct_option));
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("marks").setValue(Integer.valueOf(list.get(i).question_marks));
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("option1").setValue(list.get(i).option_1.toString());
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("option2").setValue(list.get(i).option_2.toString());
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("option3").setValue(list.get(i).option_3.toString());
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("option4").setValue(list.get(i).option_4.toString());
                            dbActions.databaseReference.child("Classrooms")
                                    .child(classSelected.getName()).child("quizes")
                                    .child("quiz" + String.valueOf(quizCount))
                                    .child("question" + String.valueOf(i+1))
                                    .child("question").setValue(list.get(i).quiz_question.toString());
                        }
                    }

                    Log.d("found", snapshot.child("quizes").getKey());
                    Toast.makeText(getApplicationContext(), "Quiz Submitted successfully", Toast.LENGTH_SHORT).show();

                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        else
        {
            Toast.makeText(this, "Please Enter atleast one quiz question!", Toast.LENGTH_SHORT).show();
        }

    }
}