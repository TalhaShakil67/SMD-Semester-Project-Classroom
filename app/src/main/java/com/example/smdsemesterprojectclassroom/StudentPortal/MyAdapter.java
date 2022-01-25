package com.example.smdsemesterprojectclassroom.StudentPortal;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdsemesterprojectclassroom.MyClassModelForAdapter;
import com.example.smdsemesterprojectclassroom.R;

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


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define listener member variable
    private MyAdapter.OnItemClickListener listener;
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(MyAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView classCodeText, classNameText, numberOfStudentsText;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            classCodeText = itemView.findViewById(R.id.classcodetxt);
            classNameText = itemView.findViewById(R.id.classnametxt);
            numberOfStudentsText = itemView.findViewById(R.id.numstudentstxt);
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
