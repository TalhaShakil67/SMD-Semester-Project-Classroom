package com.example.smdsemesterprojectclassroom;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    ArrayList<MyClassModelForAdapter> classesList;
    public MyAdapter(ArrayList<MyClassModelForAdapter> classes)
    {
        classesList = classes;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_joined_classes_template, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.classCodeText.setText(classesList.get(position).getCode());
        holder.classNameText.setText(classesList.get(position).getName());
        holder.numberOfStudentsText.setText(String.valueOf(classesList.get(position).getNumberOfStudents().toString()));


    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView classCodeText, classNameText, numberOfStudentsText;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            classCodeText = itemView.findViewById(R.id.classcodetxt);
            classNameText = itemView.findViewById(R.id.classnametxt);
            numberOfStudentsText = itemView.findViewById(R.id.numstudentstxt);
        }
    }
}
