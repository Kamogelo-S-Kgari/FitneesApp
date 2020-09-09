package com.example.myhealth;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Stopwatch extends AppCompatActivity {

    // Variables used in this class:
    private TextView timer ;
    private Button start, pause, reset, home;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        timer = findViewById(R.id.stopwatch);

        start = findViewById(R.id.btn_start_watch);
        pause = findViewById(R.id.btn_pause_watch);
        reset = findViewById(R.id.btn_reset_watch);
        home = findViewById(R.id.back);

        handler = new Handler();

        // Button starts the Stopwatch:
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);
            }
        });

        // Button pauses the Stopwatch:
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);
            }
        });

        // Button resets the Stopwatch:
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                timer.setText("00:00:00");
            }
        });

        // Button takes user to the HomePage activity:
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Stopwatch.this, HomePage.class));
                finish();
            }
        });
    }

    public Runnable runnable = new Runnable()
    {

        public void run()
        {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
}
