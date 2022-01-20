package com.example.smdsemesterprojectclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyClassesActivity extends AppCompatActivity {

    DbActions dbActions;
    EditText classCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);

        dbActions = new DbActions();
        classCode = findViewById(R.id.edittxtenterclasscode);

        //classCode.setText("HBXA2PJQ1F");
        classCode.setText("ZSC07BW78I");
    }

    public void JoinClass(View view)
    {
        Query query = dbActions.databaseReference.child("Classrooms").orderByChild("code").equalTo(classCode.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren())
                {

                    Log.d("class", "Class found "+snap.child("name").getValue().toString());

                    /*if(snap.hasChild("Students"))
                    {
                        ArrayList<StudentModel> std = (ArrayList<StudentModel>) snap.child("Students").getValue();
                    }
                    else
                    {
                        ArrayList<StudentModel> std = new ArrayList<>();
                        std.add()
                    }*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}