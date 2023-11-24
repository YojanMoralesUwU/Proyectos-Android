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

public class Bajas extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener  {
    Button regresoo, Buscar;
    EditText codigo,nomb,edad,num,fech,raz;

    Spinner cont, sico;
    RadioButton homcc, mujcc, otrocc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        cont = findViewById(R.id.Spinnersito1cb);
        sico = findViewById(R.id.Spinnersito2cb);
        regresoo = findViewById(R.id.regresoteb);
        regresoo.setOnClickListener(this);
        Buscar = findViewById(R.id.Buscarb);
        Buscar.setOnClickListener(this);
        codigo = findViewById(R.id.codUsuario1b);
        nomb = findViewById(R.id.NombreUsuarioCb);
        edad = findViewById(R.id.EdadUsuariocb);
        num = findViewById(R.id.NumeroTelefonoUsuariocb);
        fech = findViewById(R.id.FechaIngresoUsuariocb);
        raz = findViewById(R.id.RazonIngresocb);
        homcc = findViewById(R.id.hombreRadioButtonCCb);
        mujcc = findViewById(R.id.mujerRadioButtonCCb);
        otrocc = findViewById(R.id.otroRadioButtonCCb);

        ArrayAdapter unidad = ArrayAdapter.createFromResource(this, R.array.units , android.R.layout.simple_spinner_item);
        cont.setAdapter(unidad);
        cont.setOnItemSelectedListener(this);
        sico.setEnabled(false);
        cont.setEnabled(false);
    }
    int opc;
    public void Editar(){
        codigo.setEnabled(false);
    }

    public void Reset(){
        codigo.setText("");
        nomb.setText("");
        edad.setText("");
        num.setText("");
        fech.setText("");
        raz.setText("");
        mujcc.setChecked(false);
        homcc.setChecked(false);
        otrocc.setChecked(false);
        cont.setSelection(0);
        sico.setSelection(0);
    }

    public void terEditar(){
        codigo.setEnabled(true);
        nomb.setEnabled(false);
        edad.setEnabled(false);
        num.setEnabled(false);
        fech.setEnabled(false);
        raz.setEnabled(false);
        homcc.setEnabled(false);
        mujcc.setEnabled(false);
        otrocc.setEnabled(false);
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

    public String generos(){
        String cual = null;
        if (homcc.isChecked()){
            cual = "Hombre";
        } else if (mujcc.isChecked()){
            cual = "Mujer";
        } else if (otrocc.isChecked()){
            cual = "Otro";
        }
        return cual;
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
                    Buscar.setText("Borrar");
                    basesita.close();
                } else{
                    mensajito = "no existe el registro";
                }
                AlertDialog.Builder mensa = new AlertDialog.Builder(this);
                    mensa.setTitle("Buen Dia");
                mensa.setMessage(mensajito);
                mensa.setPositiveButton("aceptar", null);
                AlertDialog dialog= mensa.create();
                dialog.show();
            }
        } else if (cadenita.equals("Borrar")) {
            BasesitaUsuarios admin = new BasesitaUsuarios(this, "administracion", null, 1);
            SQLiteDatabase basesita = admin.getWritableDatabase();
            int codiguito = Integer.parseInt(codigo.getText().toString());

            if (!nomb.getText().toString().equals("")
                    && !num.getText().toString().equals("") && !fech.getText().toString().equals("") && !raz.getText().toString().equals("")
                    && !cont.getSelectedItem().toString().equals("Selecciona") && !sico.getSelectedItem().toString().equals("Selecciona") && !edad.getText().toString().equals("")) {

                basesita.execSQL("DELETE FROM pacien WHERE pNum=" + codiguito);


                basesita.close();
                Toast.makeText(this, "Eliminado", Toast.LENGTH_LONG).show();
                terEditar();
                Reset();
                Buscar.setText("Buscar");
            } else {
                Toast.makeText(this, "INGRESA TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
            }
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