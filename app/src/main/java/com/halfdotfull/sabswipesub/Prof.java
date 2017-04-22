package com.halfdotfull.sabswipesub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Prof extends AppCompatActivity {
    EditText name,password;
    Button btn,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        name= (EditText) findViewById(R.id.profName);
        btn= (Button) findViewById(R.id.profLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Prof.this,ProfLogged.class);
                i.putExtra("Name",name.getText().toString());
                startActivity(i);
            }
        });
    }
}
