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

public class Cambios extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button regresoo, Buscar;
    EditText codigo,nomb,edad,num,fech,raz,yen;

    Spinner cont, sico;
    RadioButton homcc, mujcc, otrocc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        cont = findViewById(R.id.Spinnersito1cc);
        sico = findViewById(R.id.Spinnersito2cc);
        regresoo = findViewById(R.id.regresito);
        regresoo.setOnClickListener(this);
        Buscar = findViewById(R.id.BuscaryCambiar);
        Buscar.setOnClickListener(this);
        codigo = findViewById(R.id.codUsuario11);
        nomb = findViewById(R.id.NombreUsuarioCc);
        edad = findViewById(R.id.EdadUsuariocc);
        num = findViewById(R.id.NumeroTelefonoUsuariocc);
        fech = findViewById(R.id.FechaIngresoUsuariocc);
        raz = findViewById(R.id.RazonIngresocc);
        homcc = findViewById(R.id.hombreRadioButtonC);
        mujcc = findViewById(R.id.mujerRadioButtonC);
        otrocc = findViewById(R.id.otroRadioButtonC);

        ArrayAdapter unidad = ArrayAdapter.createFromResource(this, R.array.units , android.R.layout.simple_spinner_item);
        cont.setAdapter(unidad);
        cont.setOnItemSelectedListener(this);
    }
    int opc;
    public void Editar(){
        codigo.setEnabled(false);
        nomb.setEnabled(true);
        edad.setEnabled(true);
        num.setEnabled(true);
        fech.setEnabled(true);
        raz.setEnabled(true);
        homcc.setEnabled(true);
        mujcc.setEnabled(true);
        otrocc.setEnabled(true);
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

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
                    Editar();
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
                        opc = sicologo(fila.getString(7));
                    } else if (fila.getString(6).equals("Psiquiatras")){
                        ArrayAdapter lista2 = ArrayAdapter.createFromResource(this,R.array.Psiquiatras, android.R.layout.simple_list_item_1);
                        sico.setAdapter(lista2);
                        sico.setSelection(siquitra(fila.getString(7)));
                        opc = siquitra(fila.getString(7));
                    }

                    mensajito= "estos son los datos";
                    Buscar.setText("Cambios");
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
        } else if (cadenita.equals("Cambios")){

        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getItemAtPosition(i).equals("Psicologos")){
            ArrayAdapter lista = ArrayAdapter.createFromResource(this,R.array.Psicologos, android.R.layout.simple_list_item_1);
            sico.setAdapter(lista);
            sico.setSelection(opc);
            opc = 0;
        } else if (adapterView.getItemAtPosition(i).equals("Psiquiatras")){
            ArrayAdapter lista2 = ArrayAdapter.createFromResource(this,R.array.Psiquiatras, android.R.layout.simple_list_item_1);
            sico.setAdapter(lista2);
            sico.setSelection(opc);
            opc = 0;
        }
    }
}