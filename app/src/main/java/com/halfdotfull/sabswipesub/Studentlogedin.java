package com.halfdotfull.sabswipesub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Studentlogedin extends AppCompatActivity {
    DatabaseReference mDatabase;
    ToggleButton toggle1,toggle2,toggle3,toggle4;
    ToggleButton toggle5,toggle6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogedin);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //toggle1=findViewById(R.id.toggle);

    }
}
