package com.example.kronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //button cronometro
    public void showCrono(View view){
        Intent intent = new Intent(this, CronoActivity.class);
        startActivity(intent);
    }
    //buton temporizador
    public void showTemp(View view){
        Toast.makeText(this, "Funcionalidade não disponivel", Toast.LENGTH_SHORT).show();
    }
}