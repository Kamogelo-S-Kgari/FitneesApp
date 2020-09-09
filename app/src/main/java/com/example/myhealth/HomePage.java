package com.example.myhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.myhealth.R.id.btn_stopwatch;

public class HomePage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private Button buttonBMI, buttonCountSteps, buttonStopwatch, buttonExercise, buttonPicture, buttonProfile, buttonLocation, buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        buttonPicture = findViewById(R.id.btn_take_picture);
        buttonBMI = findViewById(R.id.btn_calculate_bmi);
        buttonCountSteps = findViewById(R.id.count_steps);
        buttonExercise = findViewById(R.id.btn_exercises);
        buttonStopwatch = findViewById(btn_stopwatch);
        buttonLogOut = findViewById(R.id.btn_logout);
        buttonProfile = findViewById(R.id.btn_view_profile);
        buttonLocation = findViewById(R.id.btn_view_location);


        //This method will display the CalculateBMI activity:
        buttonBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, CalculateBMI.class));
                finish();
            }
        });

        //This method will display the CountSteps activity:
        buttonCountSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, CountSteps.class));
                finish();
            }
        });

        //This method opens the ViewExercises activity:
        buttonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, ViewExercises.class));
                finish();
            }
        });

        buttonStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Stopwatch.class));
                finish();
            }
        });

        //Method opens the ViewProfile activity:
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, UserProfile.class));
                finish();
            }
        });

        // Button allows the user to view their current location:
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Location.class));
                finish();
            }
        });

        //This method will return the user to the MainActivity:
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MainActivity.class));
                finish();
            }
        });

        //This opens the TakePicture activity:
        buttonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, TakePicture.class));
                finish();
            }
        });
    }
}
