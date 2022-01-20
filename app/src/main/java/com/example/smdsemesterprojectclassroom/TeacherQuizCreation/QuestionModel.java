package com.example.smdsemesterprojectclassroom.TeacherQuizCreation;

public class QuestionModel {

    String quiz_question;
    String option_1;
    String option_2;
    String option_3;
    String option_4;
    int correct_option;
    int question_marks;

    public QuestionModel(String quiz_question, String option_1, String option_2, String option_3, String option_4, int correct_option, int question_marks) {
        this.quiz_question = quiz_question;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.option_4 = option_4;
        this.correct_option = correct_option;
        this.question_marks = question_marks;
    }

    public String getQuiz_question() {
        return quiz_question;
    }

    public void setQuiz_question(String quiz_question) {
        this.quiz_question = quiz_question;
    }

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getOption_3() {
        return option_3;
    }

    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    public String getOption_4() {
        return option_4;
    }

    public void setOption_4(String option_4) {
        this.option_4 = option_4;
    }

    public int getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(int correct_option) {
        this.correct_option = correct_option;
    }

    public int getQuestion_marks() {
        return question_marks;
    }

    public void setQuestion_marks(int question_marks) {
        this.question_marks = question_marks;
    }
}
