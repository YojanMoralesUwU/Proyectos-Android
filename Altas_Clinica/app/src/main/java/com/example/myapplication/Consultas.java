package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class Consultas extends AppCompatActivity implements View.OnClickListener {
    Button regresoo, Buscar;
    EditText codigo,nomb,edad,num,fech,raz,yen;
    TextView tipoD,nombreD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        regresoo = findViewById(R.id.regresote);
        regresoo.setOnClickListener(this);
        Buscar = findViewById(R.id.Buscar);
        Buscar.setOnClickListener(this);

        codigo = findViewById(R.id.codUsuario1);
        nomb = findViewById(R.id.NombreUsuarioC);
        edad = findViewById(R.id.EdadUsuarioc);
        yen = findViewById(R.id.yen);
        num = findViewById(R.id.NumeroTelefonoUsuarioc);
        fech = findViewById(R.id.FechaIngresoUsuarioc);
        raz = findViewById(R.id.RazonIngresoc);
        tipoD = findViewById(R.id.tipoDocc);
        nombreD = findViewById(R.id.NombreDocc);


    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Regresote")){
            Intent regresoI = new Intent(this, MainActivity2.class);
            startActivity(regresoI);
        } else if (cadenita.equals("Buscar")){
            String mensajito= "";
            if (codigo.getText().toString().equals("")){
                Toast.makeText(this, "Ingrese codigo valido",Toast.LENGTH_LONG);
            } else {
                BasesitaUsuarios admin = new BasesitaUsuarios(this, "administracion",null, 1);
                SQLiteDatabase basesita  = admin.getWritableDatabase();
                int codiguito = Integer.parseInt(codigo.getText().toString());
                Cursor fila = basesita.rawQuery("select pNom, pEdad, pGen, pTel, pFeIn, pRaIn, pPNom, pTp from pacien where pNum ="+codiguito, null);
                if (fila.moveToFirst()){

                    nomb.setText(fila.getString(0));
                    edad.setText(fila.getString(1));
                    yen.setText(fila.getString(2));
                    num.setText(fila.getString(3));
                    fech.setText(fila.getString(4));
                    raz.setText(fila.getString(5));
                    nombreD.setText(fila.getString(6));
                    tipoD.setText(fila.getString(7));
                    mensajito= "estos son los datos";
                    basesita.close();
                } else{
                    mensajito = "no existe el registro";
                }
                AlertDialog.Builder mensa = new AlertDialog.Builder(this);
                mensa.setTitle("hola");
                mensa.setMessage(mensajito);
                mensa.setPositiveButton("aceptar", null);
                AlertDialog dialog= mensa.create();
                dialog.show();
        }
    }
}
}