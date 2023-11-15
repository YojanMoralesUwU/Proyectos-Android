    package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button psicologos, usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        psicologos = findViewById(R.id.Psicologos);
        psicologos.setOnClickListener(this);

        usuarios = findViewById(R.id.Usuarios);
        usuarios.setOnClickListener(this);


    }

        @Override
        public void onClick(View v) {
            String cadenita = ((Button)v).getText().toString();
            if (cadenita.equals("Usuarios")){
                Intent cambiaraUsuarios = new Intent(this,MainActivity2.class);
                startActivity(cambiaraUsuarios);
            }else if (cadenita.equals("Psicologos")){
                Intent cambiaraPsicologos = new Intent(this, MainActivity3.class);
                startActivity(cambiaraPsicologos);
            }
        }
    }