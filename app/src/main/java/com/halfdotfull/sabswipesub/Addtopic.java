package com.halfdotfull.sabswipesub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addtopic extends AppCompatActivity {
    DatabaseReference mDatabase;
    EditText topicname,sub,year,branch;
    Button submit;
    Topic addtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtopic);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        topicname= (EditText) findViewById(R.id.topicname);
        sub= (EditText) findViewById(R.id.subject);
        year= (EditText) findViewById(R.id.year);
        branch= (EditText) findViewById(R.id.branch);
        submit= (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtop=new Topic(branch.getText().toString(),Integer.valueOf(year.getText().toString()),topicname.getText().toString(),sub.getText().toString(),getIntent().getStringExtra("nameprof"));
                Toast.makeText(Addtopic.this, getIntent().getStringExtra("nameprof"), Toast.LENGTH_SHORT).show();
                mDatabase.child("profadded").push().setValue(addtop);
            }
        });
    }
}
