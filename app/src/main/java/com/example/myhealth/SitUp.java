package com.example.myhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SitUp extends AppCompatActivity {

    private Button buttonExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_up);

        buttonExercises = findViewById(R.id .back);

        buttonExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SitUp.this, ViewExercises.class));
                finish();
            }
        });
    }
}
