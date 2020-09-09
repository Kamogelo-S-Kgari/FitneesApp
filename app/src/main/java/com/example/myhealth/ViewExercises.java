package com.example.myhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ViewExercises extends AppCompatActivity {
    // Variables used for this class:
    private Button buttonStretch, buttonJog, buttonCrunch, buttonPush, buttonSquat, buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);

        buttonStretch = findViewById(R.id.btn_stretch);
        buttonJog = findViewById(R.id.btn_jog);
        buttonPush = findViewById(R.id.btn_pushup);
        buttonSquat = findViewById(R.id.btn_squat);
        buttonCrunch = findViewById(R.id.btn_situp);
        buttonHome = findViewById(R.id.btn_back);

        // Button opens the PushupExercise activity:
        buttonPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercises.this, PushupExercise.class));
                finish();
            }
        });

        // Button opens the SquatExercise activity:
        buttonSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercises.this, SquatExercise.class));
                finish();
            }
        });

        // Button opens the JogExercise activity:
        buttonJog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercises.this, JogExercise.class));
                finish();
            }
        });

        // Button opens the SitUp activity:
        buttonCrunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercises.this, SitUp.class));
                finish();
            }
        });

        // Button opens the Stretching activity:
        buttonStretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercises.this, Stretching.class));
                finish();
            }
        });

        // Button returns the user to the HomePage activity:
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercises.this, HomePage.class));
                finish();
            }
        });
    }
}
