package com.halfdotfull.sabswipesub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfLogged extends AppCompatActivity {
    DatabaseReference mDatabase;
    Button addtopic,seetopic;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_logged);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        addtopic= (Button) findViewById(R.id.addTopic);
        seetopic= (Button) findViewById(R.id.seeStatus);
        addtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ProfLogged.this,Addtopic.class);
                name=getIntent().getStringExtra("Name");
                i.putExtra("nameprof",name);
                startActivity(i);
            }
        });
    }
}
