package com.example.kronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CronoActivity extends AppCompatActivity {

    private TextView txtViewTime;
    private Button btnStart;
    private boolean exe, inExe;
    int sec = 0, min = 0, hour = 0;
    Handler handler = new Handler();
    Runnable runnable;
    int aux=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crono);
        if(savedInstanceState != null){
            sec = savedInstanceState.getInt("sec");
            min = savedInstanceState.getInt("min");
            hour = savedInstanceState.getInt("hour");
            exe = savedInstanceState.getBoolean("exe");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected  void onSaveInstanceState(Bundle savedIntanceState){
        super.onSaveInstanceState(savedIntanceState);
        savedIntanceState.putInt("sec", sec);
        savedIntanceState.putInt("min", min);
        savedIntanceState.putInt("hour", hour);
        savedIntanceState.putBoolean("exe", exe);
    }
    @Override
    protected  void onPause(){
        super.onPause();
        inExe = exe;
        exe = false;
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(inExe){
            exe = true;
        }
    }

    //button start
    public void clickStart(View view) {
        exe = true;
        if(aux==0){
            aux=1;

            txtViewTime = findViewById(R.id.txtViewTime);

            btnStart = findViewById(R.id.btnStartCrono);
            btnStart.setText(R.string.stop);
            btnStart.setBackgroundColor(getResources().getColor(R.color.red));

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
        }else{
            stop();
        }
    }

    //stop
    public void stop() {
        exe = false;
        if(aux==1){
            btnStart.setText(R.string.resume);
            btnStart.setBackgroundColor(getResources().getColor(R.color.green));
            handler.removeCallbacks(runnable);
            //volta para start
            aux = 0;
        }
    }

    //button reset
    public void clickReset(View view) {
        handler.removeCallbacks(runnable);
        exe = false;
        sec = 0;
        min = 0;
        hour = 0;
        txtViewTime.setText("0:00:00");
        btnStart.setText(R.string.start);
    }
}