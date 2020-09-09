package com.example.myhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SquatExercise extends AppCompatActivity {

    // Variables used for this class:
    private Button buttonExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat_exercise);

        buttonExercises = findViewById(R.id.back);

        buttonExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SquatExercise.this, ViewExercises.class));
                finish();
            }
        });
    }
}
