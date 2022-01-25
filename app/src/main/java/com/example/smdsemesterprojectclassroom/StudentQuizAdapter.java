package com.example.smdsemesterprojectclassroom;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherClassRecAdapter;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherQuizCreation.QuestionModel;

import java.util.ArrayList;

public class StudentQuizAdapter extends RecyclerView.Adapter<StudentQuizAdapter.ViewHolder>{

    ArrayList<QuestionModel> list;
    ArrayList<Integer> answers;
    public StudentQuizAdapter(ArrayList<QuestionModel> list) {
        this.list = list;
        answers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_quiz_rec, parent,false);
        return new StudentQuizAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        answers.add(0);
        int index = position;
        holder.quiz_option_a.setText(list.get(position).getOption_1());
        holder.quiz_option_b.setText(list.get(position).getOption_2());
        holder.quiz_option_c.setText(list.get(position).getOption_3());
        holder.quiz_option_d.setText(list.get(position).getOption_4());
        holder.quiz_question.setText(list.get(position).getQuiz_question());
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.student_r_opt_1:
                        answers.set(index,1);
                        break;
                    case R.id.student_r_opt_2:
                        answers.set(index,2);
                        break;
                    case R.id.student_r_opt_3:
                        answers.set(index,3);
                        break;
                    case R.id.student_r_opt_4:
                        answers.set(index,4);
                        break;
                    default:
                        answers.set(index,0);
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define listener member variable
    private StudentQuizAdapter.OnItemClickListener listener;
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(StudentQuizAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView quiz_question;
        TextView quiz_option_a;
        TextView quiz_option_b;
        TextView quiz_option_c;
        TextView quiz_option_d;
        RadioGroup radioGroup;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            quiz_question = itemView.findViewById(R.id.student_quiz_question_edttxt);
            quiz_option_a = itemView.findViewById(R.id.student_quiz_option_a1);
            quiz_option_b = itemView.findViewById(R.id.student_quiz_option_b1);
            quiz_option_c = itemView.findViewById(R.id.student_quiz_option_c1);
            quiz_option_d = itemView.findViewById(R.id.student_quiz_option_d1);
            radioGroup = itemView.findViewById(R.id.student_r_opt);
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
