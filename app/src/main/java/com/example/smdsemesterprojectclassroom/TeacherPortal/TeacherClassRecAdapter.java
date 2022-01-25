package com.example.smdsemesterprojectclassroom.TeacherPortal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdsemesterprojectclassroom.R;

import java.util.ArrayList;

public class TeacherClassRecAdapter extends RecyclerView.Adapter<TeacherClassRecAdapter.ViewHolder>
{
    ArrayList<TeacherClassModel> classesList;

    public TeacherClassRecAdapter(ArrayList<TeacherClassModel> classes)
    {
        classesList = classes;
    }

    @NonNull
    @Override
    public TeacherClassRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_class_rec, parent,false);
        return new TeacherClassRecAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherClassRecAdapter.ViewHolder holder, int position) {

        holder.classCodeText.setText("Classroom Code: " + classesList.get(position).getCode().toString());
        holder.classNameText.setText("Class: " + classesList.get(position).getName().toString());
        holder.studentCount.setText("Total Students: " + String.valueOf(classesList.get(position).getNumberOfStudents()) );
    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define listener member variable
    private OnItemClickListener listener;
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView classCodeText, classNameText, studentCount;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            classCodeText = itemView.findViewById(R.id.teacher_class_rec_classcode);
            classNameText = itemView.findViewById(R.id.teacher_class_rec_txt1);
            studentCount = itemView.findViewById(R.id.teacher_class_rec_studentcount);
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