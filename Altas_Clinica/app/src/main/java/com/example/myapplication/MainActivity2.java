package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button altas,bajas,cambios,consultas,regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        altas = findViewById(R.id.Altitas);
        bajas = findViewById(R.id.Bajas);
        cambios = findViewById(R.id.Cambios);
        consultas = findViewById(R.id.Consultas);
        regresar = findViewById(R.id.RegresarInicioDesdeUsuarios);

        altas.setOnClickListener(this);
        bajas.setOnClickListener(this);
        cambios.setOnClickListener(this);
        consultas.setOnClickListener(this);
        regresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Altas")){
            Intent irAltas =  new Intent(this,MainActivity4.class);
            startActivity(irAltas);
        }else if (cadenita.equals("Regresar")){
            Intent irInicio = new Intent(this,MainActivity.class);
            startActivity(irInicio);
        }
    }
}