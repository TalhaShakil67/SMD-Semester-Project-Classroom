package com.example.smdsemesterprojectclassroom;

import androidx.annotation.NonNull;

import com.example.smdsemesterprojectclassroom.StudentPortal.StudentModel;
import com.example.smdsemesterprojectclassroom.TeacherPortal.TeacherModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DbActions
{
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    Integer currentTeacherID, currentStudentID;

    public DbActions()
    {
        firebaseDatabase = FirebaseDatabase.getInstance("https://smd-semester-project-portal-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference();
        updateAllTeachersCount();
        updateAllStudentsCount();
    }

    public void updateAllStudentsCount()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer maxID = 0;
                for(DataSnapshot student : snapshot.child("Students").getChildren())
                {
                    if(Integer.parseInt(student.child("id").getValue().toString()) > maxID)
                    {
                        maxID = Integer.parseInt(student.child("id").getValue().toString());
                    }
                }
                currentStudentID = maxID + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateAllTeachersCount()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer maxID = 0;
                for(DataSnapshot teacher : snapshot.child("Teachers").getChildren())
                {
                    if(Integer.parseInt(teacher.child("id").getValue().toString()) > maxID)
                    {
                        maxID = Integer.parseInt(teacher.child("id").getValue().toString());
                    }
                }
                currentTeacherID = maxID + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Integer getCurrentTeacherID()
    {
        updateAllTeachersCount();;
        return currentTeacherID;
    }

    public Integer getCurrentStudentID()
    {
        updateAllStudentsCount();
        return currentStudentID;
    }

    public void addTeacher(TeacherModel teacher)
    {
        databaseReference.child("Teachers").child(teacher.getName()).setValue(teacher);
    }
    public void addStudent(StudentModel student)
    {
        databaseReference.child("Students").child(student.getName()).setValue(student);
    }
    public void createClassroom(ClassroomModel classroom)
    {
        databaseReference.child("Classrooms").child(classroom.getName()).setValue(classroom);
    }
}
