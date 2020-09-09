package com.example.myhealth;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CountSteps extends AppCompatActivity implements SensorEventListener {

    TextView steps;
    SensorManager sensorManager;
    private Button buttonHome;

    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_steps);

        steps = findViewById(R.id.counter_results);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        buttonHome = findViewById(R.id.back);

        //Button returns user to the HomePage:
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountSteps.this, HomePage.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        running = true;

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(countSensor != null){

            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        }else{

            Toast.makeText(this,"Sensor not found!",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        running = false;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        steps.setText(String.valueOf(event.values[0]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
