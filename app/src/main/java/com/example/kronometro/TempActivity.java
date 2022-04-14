package com.example.kronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TempActivity extends AppCompatActivity {
    private EditText inputSec, inputMin, inputHour;
    private Button btnStart, btnReset;
    private boolean exe, inExe;
    int sec = 0, min = 0, hour = 0;
    Handler handler = new Handler();
    Runnable runnable;
    int aux=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        inputSec = findViewById(R.id.inputSec);
        inputMin = findViewById(R.id.inputMin);
        inputHour = findViewById(R.id.inputHour);

        btnStart = findViewById(R.id.btnStartTemp);
        btnReset = findViewById(R.id.btnResetTemp);


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
    public void clickStart(View view){
        exe = true;

        inputSec.setFocusable(false);
        inputSec.setClickable(false);

        inputMin.setFocusable(false);
        inputMin.setClickable(false);

        inputHour.setFocusable(false);
        inputHour.setClickable(false);

        //pegando valores
        sec = Integer.parseInt(inputSec.getText().toString());
        min = Integer.parseInt(inputMin.getText().toString());
        hour = Integer.parseInt(inputHour.getText().toString());

        if(aux==0){

            aux=1;

            btnStart.setText(R.string.stop);
            btnStart.setBackgroundColor(getResources().getColor(R.color.red));

            handler.postDelayed(runnable = new Runnable() {
                @Override
                public void run() {
                    if(sec == 0 && min == 0 && hour == 0){
                        sec = 0;
                        min = 0;
                        hour = 0;
                        handler.removeCallbacks(runnable);
                        Toast.makeText(TempActivity.this, "O tempo acabou", Toast.LENGTH_SHORT).show();

                    }else{
                        if(sec > 0){
                            sec--;
                            inputSec.setText(String.valueOf(sec));
                        }if(sec == 0 && min>0){
                            sec = 59;
                            min--;
                            inputSec.setText(String.valueOf(sec));
                            inputMin.setText(String.valueOf(min));
                            inputHour.setText(String.valueOf(hour));
                        }if(min == 00 && hour>0){
                            sec = 59;
                            min = 59;
                            hour--;
                            inputMin.setText(String.valueOf(min));
                            inputHour.setText(String.valueOf(hour));
                        }

                        handler.postDelayed(this, 1000);
                    }

                }
            }, 1000);
        }
        else{
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

        inputSec.setFocusable(true);
        inputSec.setClickable(true);

        inputMin.setFocusable(true);
        inputMin.setClickable(true);

        inputHour.setFocusable(true);
        inputHour.setClickable(true);

        inputSec.setText("00");
        inputMin.setText("00");
        inputHour.setText("0");
        btnStart.setText(R.string.start);
    }

}