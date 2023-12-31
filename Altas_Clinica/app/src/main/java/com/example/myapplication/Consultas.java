package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Consultas extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button regresoo, Buscar;
    EditText codigo,nomb,edad,num,fech,raz,yen;

    Spinner cont, sico;
    RadioButton homcc, mujcc, otrocc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        cont = findViewById(R.id.Spinnersito1c);
        sico = findViewById(R.id.Spinnersito2c);
        regresoo = findViewById(R.id.regresote);
        regresoo.setOnClickListener(this);
        Buscar = findViewById(R.id.Buscar);
        Buscar.setOnClickListener(this);

        codigo = findViewById(R.id.codUsuario1);
        nomb = findViewById(R.id.NombreUsuarioC);
        edad = findViewById(R.id.EdadUsuarioc);
        num = findViewById(R.id.NumeroTelefonoUsuarioc);
        fech = findViewById(R.id.FechaIngresoUsuarioc);
        raz = findViewById(R.id.RazonIngresoc);
        homcc = findViewById(R.id.hombreRadioButtonCC);
        mujcc = findViewById(R.id.mujerRadioButtonCC);
        otrocc = findViewById(R.id.otroRadioButtonCC);

        ArrayAdapter unidad = ArrayAdapter.createFromResource(this, R.array.units , android.R.layout.simple_spinner_item);
        cont.setAdapter(unidad);
        cont.setOnItemSelectedListener(this);
        cont.setEnabled(false);

        sico.setEnabled(false);

    }

    public int eleccion(String hola){
        int cual = 0;
        if (hola.equals("Psicologos")){
            cual = 1;
        } else if (hola.equals("Psiquiatras")){
            cual = 2;
        }
        return cual;
    }

    public int sicologo(String hola){
        int cual = 0;
        if (hola.equals("Armando Fuentes")){
            cual = 1;
        } else if (hola.equals("Jorge Morales")){
            cual = 2;
        } else if (hola.equals("Axel Maldonado")){
            cual = 3;
        }
        return cual;
    }

    public int siquitra(String hola){
        int cual = 0;
        if (hola.equals("Luis Frias")){
            cual = 1;
        } else if (hola.equals("Fernando Mendoza")){
            cual = 2;
        }
        return cual;
    }

    public void generoCir(String hola){
        if (hola.equals("Mujer")){
            mujcc.setChecked(true);
        } else if (hola.equals("Hombre")){
            homcc.setChecked(true);
        } else if (hola.equals("Otro")){
            otrocc.setChecked(true);
        }
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
                    generoCir((fila.getString(2)));
                    num.setText(fila.getString(3));
                    fech.setText(fila.getString(4));
                    raz.setText(fila.getString(5));
                    cont.setSelection(eleccion(fila.getString(6)));
                    if(fila.getString(6).equals("Psicologos")){
                        ArrayAdapter lista = ArrayAdapter.createFromResource(this,R.array.Psicologos, android.R.layout.simple_list_item_1);
                        sico.setAdapter(lista);
                        sico.setSelection(sicologo(fila.getString(7)));
                    } else if (fila.getString(6).equals("Psiquiatras")){
                        ArrayAdapter lista2 = ArrayAdapter.createFromResource(this,R.array.Psiquiatras, android.R.layout.simple_list_item_1);
                        sico.setAdapter(lista2);
                        sico.setSelection(siquitra(fila.getString(7)));
                    }




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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}