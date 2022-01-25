package com.example.smdsemesterprojectclassroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizTilesAdapter extends RecyclerView.Adapter<QuizTilesAdapter.ViewHolder>{


    ArrayList<QuizModel> quizList;

    public QuizTilesAdapter(ArrayList<QuizModel> quizList) {
        this.quizList = quizList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_class_quiz, parent,false);
        return new QuizTilesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.quizTitle.setText(quizList.get(position).getQuizTitle());
        holder.quizscore.setText("Total Marks: " + String.valueOf(quizList.get(position).getQuizScore()));
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define listener member variable
    private QuizTilesAdapter.OnItemClickListener listener;
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(QuizTilesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView quizTitle, quizscore;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            quizTitle = itemView.findViewById(R.id.quiz_s_h1);
            quizscore = itemView.findViewById(R.id.quiz_s_score);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(itemView, position);
                }
            }
        }
    }
}
