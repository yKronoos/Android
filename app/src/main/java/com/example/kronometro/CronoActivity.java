package com.example.kronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CronoActivity extends AppCompatActivity {

    public TextView txtViewTime;
    public Button btnStart;
    int sec = 0, min = 0, hour = 0;
    Handler handler = new Handler();
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crono);
    }

    //button start
    public void start(View view) {

        txtViewTime = findViewById(R.id.txtViewTime);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setClickable(false);

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                sec++;
                if(sec == 59){
                    sec = 0;
                    min++;
                }if(min == 59){
                    min = 0;
                    hour++;
                }
                String time = String.format("%d:%02d:%02d", hour, min, sec);
                txtViewTime.setText(time);
                handler.postDelayed(this, 1000);
            }
        }, 1000);

    }

    //button stop
    public void stop(View view) {
        handler.removeCallbacks(runnable);
        btnStart.setClickable(true);

    }

    //button reset
    public void reset(View view) {
        handler.removeCallbacks(runnable);
        sec = 0;
        min = 0;
        hour = 0;
        txtViewTime.setText("0:00:00");
        btnStart.setClickable(true);
    }
}