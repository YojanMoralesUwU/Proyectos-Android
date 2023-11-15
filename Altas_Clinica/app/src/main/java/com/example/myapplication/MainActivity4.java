package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner contRelacionado, Psicologo;
    EditText nomCo, edad, numero, ingreso, razon;
    RadioGroup genero;
    RadioButton hom,muj,otro;
    Button subir, regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        contRelacionado = findViewById(R.id.Spinnersito1);
        Psicologo = findViewById(R.id.Spinnersito2);
        nomCo = findViewById(R.id.NombreUsuario);
        edad = findViewById(R.id.EdadUsuario);
        numero = findViewById(R.id.NumeroTelefonoUsuario);
        ingreso = findViewById(R.id.FechaIngresoUsuario);
        razon = findViewById(R.id.RazonIngreso);
        genero = findViewById(R.id.generoRadioGroup);
        hom = findViewById(R.id.hombreRadioButton);
        muj = findViewById(R.id.mujerRadioButton);
        otro = findViewById(R.id.otroRadioButton);
        subir = findViewById(R.id.Subir);
        regresar = findViewById(R.id.Regresadito);
        regresar.setOnClickListener(this);

        ArrayAdapter unidad = ArrayAdapter.createFromResource(this, R.array.units , android.R.layout.simple_spinner_item);
        contRelacionado.setAdapter(unidad);
        contRelacionado.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getItemAtPosition(i).equals("Psicologos")){
            ArrayAdapter lista = ArrayAdapter.createFromResource(this,R.array.Psicologos, android.R.layout.simple_list_item_1);
            Psicologo.setAdapter(lista);
        } else if (adapterView.getItemAtPosition(i).equals("Psiquiatras")){
            ArrayAdapter lista2 = ArrayAdapter.createFromResource(this,R.array.Psiquiatras, android.R.layout.simple_list_item_1);
            Psicologo.setAdapter(lista2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Regresar")){
            Intent regreso = new Intent(this,MainActivity2.class);
        } else if (cadenita.equals("Subir")){
            BasesitaUsuarios admin = new BasesitaUsuarios(this, "administración",null,1);
            SQLiteDatabase basesita  = admin.getWritableDatabase();
        }
    }
}