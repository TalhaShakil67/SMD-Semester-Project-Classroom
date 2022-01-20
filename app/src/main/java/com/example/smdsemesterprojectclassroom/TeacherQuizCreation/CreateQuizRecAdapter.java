package com.example.smdsemesterprojectclassroom.TeacherQuizCreation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdsemesterprojectclassroom.R;

import java.util.ArrayList;

public class CreateQuizRecAdapter extends RecyclerView.Adapter<CreateQuizRecAdapter.myHolder>{


    public ArrayList<QuestionModel> getArrayList() {
        return arrayList;
    }

    ArrayList<QuestionModel> arrayList;

    public CreateQuizRecAdapter(ArrayList<QuestionModel> arrayList) {
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.add_question_rec,parent, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.quiz_option_a.setText(arrayList.get(position).option_1);
        holder.quiz_option_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.get(position).option_1 = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.quiz_option_b.setText(arrayList.get(position).option_2);
        holder.quiz_option_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.get(position).option_2 = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.quiz_option_c.setText(arrayList.get(position).option_3);
        holder.quiz_option_c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.get(position).option_3 = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.quiz_option_d.setText(arrayList.get(position).option_4);
        holder.quiz_option_d.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.get(position).option_4 = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.quiz_question.setText(arrayList.get(position).quiz_question);
        holder.quiz_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.get(position).quiz_question = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class myHolder extends RecyclerView.ViewHolder {

        EditText quiz_question;
        EditText quiz_option_a;
        EditText quiz_option_b;
        EditText quiz_option_c;
        EditText quiz_option_d;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            quiz_question = itemView.findViewById(R.id.quiz_question_edttxt);
            quiz_option_a = itemView.findViewById(R.id.quiz_option_a1);
            quiz_option_b = itemView.findViewById(R.id.quiz_option_b1);
            quiz_option_c = itemView.findViewById(R.id.quiz_option_c1);
            quiz_option_d = itemView.findViewById(R.id.quiz_option_d1);

        }
    }
}
