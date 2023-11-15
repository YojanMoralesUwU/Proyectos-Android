package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.badge.BadgeUtils;

public class Consultas extends AppCompatActivity implements View.OnClickListener {
    Button regresoo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        regresoo = findViewById(R.id.regresote);
        regresoo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Regresote")){
            Intent regresoI = new Intent(this, MainActivity2.class);
            startActivity(regresoI);
        }
    }
}