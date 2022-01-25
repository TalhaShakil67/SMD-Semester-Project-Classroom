package com.example.smdsemesterprojectclassroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smdsemesterprojectclassroom.StudentPortal.StudentModel;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation.QuestionModel;

import java.util.ArrayList;

public class StudentQuizActivity extends AppCompatActivity {

    DbActions dbActions;
    StudentModel loggedInStudent;
    MyClassModelForAdapter myClass;
    QuizModel quize;
    StudentQuizAdapter myAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView classTitle;
    ArrayList<QuestionModel> questionsList;
    Button startQuizBtn, submitQuizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_quiz);

        quize = new QuizModel();

        Intent intent = getIntent();
        quize.setQuestionsList((ArrayList<QuestionModel>) intent.getSerializableExtra("questions"));
        quize.setQuizTitle(intent.getSerializableExtra("quiztitle").toString());
        quize.setQuizScore((Integer) intent.getSerializableExtra("quizscore"));
        loggedInStudent = (StudentModel) intent.getSerializableExtra("student");
        myClass = (MyClassModelForAdapter) intent.getSerializableExtra("class");

        //intent.putExtra("questions", quizesList.get(position).getQuestionsList());
        //intent.putExtra("quiztitle", quizesList.get(position).getQuizTitle());
        //intent.putExtra("quizscore", quizesList.get(position).getQuizTitle());
        //quize = new QuizModel();

        dbActions = new DbActions();


        classTitle = findViewById(R.id.student_quiz_h1);
        startQuizBtn = findViewById(R.id.student_quiz_start_btn);
        submitQuizBtn = findViewById(R.id.student_quiz_submit_btn);
        submitQuizBtn.setVisibility(View.INVISIBLE);

        classTitle.setText(quize.getQuizTitle());

        questionsList = new ArrayList<>();
        recyclerView = findViewById(R.id.student_quiz_recz);
        myAdapter = new StudentQuizAdapter(questionsList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

    }

    public void StartQuiz(View view) {
        startQuizBtn.setVisibility(View.INVISIBLE);
        submitQuizBtn.setVisibility(View.VISIBLE);
        questionsList.addAll(quize.getQuestionsList());
        Log.d("found", "total question:"+ String.valueOf(questionsList.size()));
        Toast.makeText(this, "Quiz Started", Toast.LENGTH_SHORT).show();
        myAdapter.notifyDataSetChanged();

    }

    public void SubmitQuiz(View view) {
        ArrayList<Integer> optionSlected = myAdapter.answers;
        float totalMarks = quize.getQuizScore();
        float questionMarks = totalMarks/(float) questionsList.size();
        int unattapted = 0;
        int wrong = 0;
        int correct = 0;
        for (int i=0; i < questionsList.size(); i++)
        {
            Log.d("found", "total question:"+ String.valueOf(optionSlected.get(i)) +" "+String.valueOf(questionsList.get(i).getCorrect_option()) );
            if (optionSlected.get(i) == 0)
            {
                unattapted +=1;
            }
            else if (optionSlected.get(i) != questionsList.get(i).getCorrect_option())
            {
                totalMarks -= questionMarks;
                wrong += 1;
            }
            else
            {
                correct +=1;
            }

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Your quiz has been submitted" +
                        "\nYour Score is " + String.valueOf(totalMarks) + " out of " + String.valueOf(quize.getQuizScore()) +
                                "\n\nCorrect answers:" + String.valueOf(correct) +
                                "\nWrong answers:" + String.valueOf(wrong) +
                                "\nUnattampted answers:" + String.valueOf(unattapted))
                .setTitle("Quiz Submited")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}