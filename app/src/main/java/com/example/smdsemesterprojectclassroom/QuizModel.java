package com.example.smdsemesterprojectclassroom;

import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation.QuestionModel;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizModel implements Serializable {
    String QuizTitle;
    int QuizScore;

    public QuizModel() {
    }

    ArrayList<QuestionModel> QuestionsList;

    public QuizModel(String quizTitle, int quizScore, ArrayList<QuestionModel> questionsList) {
        QuizTitle = quizTitle;
        QuizScore = quizScore;
        QuestionsList = questionsList;
    }

    public String getQuizTitle() {
        return QuizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        QuizTitle = quizTitle;
    }

    public int getQuizScore() {
        return QuizScore;
    }

    public void setQuizScore(int quizScore) {
        QuizScore = quizScore;
    }

    public ArrayList<QuestionModel> getQuestionsList() {
        return QuestionsList;
    }

    public void setQuestionsList(ArrayList<QuestionModel> questionsList) {
        QuestionsList = questionsList;
    }
}
