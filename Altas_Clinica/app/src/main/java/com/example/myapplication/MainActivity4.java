package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner contRelacionado, Psicologo;
    EditText nomCo, edad, numero, ingreso, razon;
    RadioGroup genero;
    RadioButton hom,muj,otro;
    Button subir, regresar;
    String nom,numTel,generito,fecIn,razIng,tipo,nombreP,edadsita;
    public String generos(){
        String cual = null;
        if (hom.isChecked()){
            cual = "Hombre";
        } else if (muj.isChecked()){
            cual = "Mujer";
        } else if (otro.isChecked()){
            cual = "Otro";
        }
        return cual;
    }

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
        subir.setOnClickListener(this);
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
        if (cadenita.equals("Regresadito")){
            Intent regreso = new Intent(this,MainActivity2.class);
            startActivity(regreso);
        } else if (cadenita.equals("Subir")){
            BasesitaUsuarios admin = new BasesitaUsuarios(this, "administración",null,1);
            SQLiteDatabase basesita  = admin.getWritableDatabase();
            nom = nomCo.getText().toString();
            edadsita = edad.getText().toString();
            generito = generos();
            numTel = numero.getText().toString();
            fecIn = ingreso.getText().toString();
            razIng = ingreso.getText().toString();
            tipo = contRelacionado.getSelectedItem().toString();
            nombreP = Psicologo.getSelectedItem().toString();
            if (!nom.equals("") && !generito.equals("")
            && !numTel.equals("") && !fecIn.equals("") && !razIng.equals("")
            && !tipo.equals("Selecciona") && !nombreP.equals("Selecciona") && !edadsita.equals("")){
                ContentValues registrito = new ContentValues();
                registrito.put("pNom",nom);
                registrito.put("pEdad",edadsita);
                registrito.put("pGen",generito);
                registrito.put("pTel",numTel);
                registrito.put("pFeIn",fecIn);
                registrito.put("pRaIn",razIng);
                registrito.put("pPNom",tipo);
                registrito.put("pTp",nombreP);
                basesita.insert("pacien", null, registrito);
                basesita.close();
                Toast.makeText(this, "agregado", Toast.LENGTH_LONG).show();
            }else Toast.makeText(this,"INGRESA TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
        }
    }
}