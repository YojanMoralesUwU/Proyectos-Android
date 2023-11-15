package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    EditText EditaditoPsicologos;
    Button SubirPsicologo, regresadito;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditaditoPsicologos = findViewById(R.id.editaditoPsicologo);

        SubirPsicologo = findViewById(R.id.subirPsicologo);
        SubirPsicologo.setOnClickListener(this);

        regresadito = findViewById(R.id.RegresarInicioDesdePsicologos);
        regresadito.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("Dar de Alta")){
            if (!EditaditoPsicologos.equals("")){
            BasesitaPsicologos admin = new BasesitaPsicologos(this, "administración",
                    null, 1);
            SQLiteDatabase basededatos = admin.getWritableDatabase();

            String nombrePsicologo = EditaditoPsicologos.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("psicologoNom", nombrePsicologo);
            basededatos.close();
            EditaditoPsicologos.setText("");
            Toast.makeText(this, "Agregado",
                    Toast.LENGTH_LONG).show();

        }else Toast.makeText(this,"Datos sin rellenar", Toast.LENGTH_LONG).show();
        }else if (cadenita.equals("Regresar")){
            Intent regresarInicio = new Intent(this, MainActivity.class);
            startActivity(regresarInicio);
        }
    }
}