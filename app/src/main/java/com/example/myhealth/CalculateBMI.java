package com.example.myhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculateBMI extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;
    private Button calculate;
    private Button returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        result = findViewById(R.id.bmi_result);
        calculate = findViewById(R.id.calculate);
        returnHome = findViewById(R.id.back);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(CalculateBMI.this, HomePage.class);
                startActivity(home);
            }
        });
    }

    private void calculateBMI() {
        String heightStr = weight.getText().toString();
        String weightStr = height.getText().toString();

        if(heightStr != null && !"".equals(heightStr) && weightStr != null && !"".equals(weightStr)){

            float weightValue = Float.parseFloat(weightStr) / 100;
            float heightValue = Float.parseFloat(heightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if(Float.compare(bmi, 15f) <= 0){
            bmiLabel = getString(R.string.extremely_underweight);

        }else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0){
            bmiLabel = getString(R.string.severely_underweight);
        }else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        }else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        }else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        }else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_1);
        }else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_2);
        }
        else {
            bmiLabel = getString(R.string.obese_class_3);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}
