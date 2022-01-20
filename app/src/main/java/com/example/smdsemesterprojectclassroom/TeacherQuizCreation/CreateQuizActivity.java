package com.example.smdsemesterprojectclassroom.TeacherQuizCreation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smdsemesterprojectclassroom.R;

import java.util.ArrayList;

public class CreateQuizActivity extends AppCompatActivity {

    CreateQuizRecAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<QuestionModel> questionList;

    TextView textView;
    String q_count = "Total Quiz Question: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);


        questionList = new ArrayList<QuestionModel>();

        textView = findViewById(R.id.create_quiz_h2);
        textView.setText(q_count + String.valueOf(questionList.size()));

        recyclerView = findViewById(R.id.create_quiz_rec);
        adapter = new CreateQuizRecAdapter(questionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
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
        Toast.makeText(this, "" + list.get(0).option_1, Toast.LENGTH_SHORT).show();
    }
}