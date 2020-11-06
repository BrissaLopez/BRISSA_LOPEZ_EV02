package com.example.brissa_lopez_ev02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class Clientes_act extends AppCompatActivity {
    private EditText codigo, nombre, salario;
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase bd ;
    ContentValues registro;
    String identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);

        codigo = (EditText)findViewById(R.id.et_cli1);
        nombre = (EditText)findViewById(R.id.et_cli2);
        salario = (EditText)findViewById(R.id.et_cli3);





    }

    public void guardarCliente(View v){
        admin = new AdminSQLiteOpenHelper(this, "bancoBPM", null, 1);
        bd = admin.getWritableDatabase();
        registro = new ContentValues();


        if(!codigo.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() || salario.getText().toString().isEmpty()){
            registro.put("codigo", codigo.getText().toString());
            registro.put("nombre", nombre.getText().toString());
            registro.put("salario", salario.getText().toString());

            bd.insert("clientes", null, registro);
            bd.close();

            Toast.makeText(this, "Se ha registrado correctamente.", Toast.LENGTH_LONG).show();
            limpiaPantalla();

        }else{

            Toast.makeText(this, "Debe llenar todos los campos.", Toast.LENGTH_LONG).show();
        }


    }

    public void limpiaPantalla(){
        codigo.setText("");
        nombre.setText("");
        salario.setText("");
    }

    public void mostrarCliente(View v){
        admin = new AdminSQLiteOpenHelper(this, "bancoBPM", null, 1);
        bd = admin.getWritableDatabase();
        registro = new ContentValues();

        identificador = codigo.getText().toString();
        if(!identificador.isEmpty()){
            Cursor fila = bd.rawQuery("SELECT codigo,nombre, salario FROM clientes WHERE codigo="+identificador, null);


            if(fila.moveToFirst()){

                codigo.setText(fila.getString(0));
                nombre.setText(fila.getString(1));
                salario.setText(fila.getString(2));
            }else{

                Toast.makeText(this, "No cliente identificado con el código ingresado.", Toast.LENGTH_LONG).show();

            }

        }else{
            Toast.makeText(this, "Debe ingresar el código identificador del cliente.", Toast.LENGTH_LONG).show();

        }
    }

    //falta agregar condiciones de eliminado

    public void eliminarCliente(View v){
        admin = new AdminSQLiteOpenHelper(this, "bancoBPM", null, 1);
        bd = admin.getWritableDatabase();
        registro = new ContentValues();

        identificador = codigo.getText().toString();
        bd.delete("clientes", "codigo=" + identificador, null);
        bd.close();
        Toast.makeText(this, "Se ha eliminado el cliente con el código " + identificador, Toast.LENGTH_LONG).show();

        limpiaPantalla();

    }

    public void actualizarCliente(View v){
        admin = new AdminSQLiteOpenHelper(this, "bancoBPM", null, 1);
        bd = admin.getWritableDatabase();
        registro = new ContentValues();

        identificador = codigo.getText().toString();

        registro.put("codigo", codigo.getText().toString());
        registro.put("nombre", nombre.getText().toString());
        registro.put("salario", salario.getText().toString());

        if(!identificador.isEmpty()){
            bd.update("clientes", registro,"codigo=" + identificador, null);

            Toast.makeText(this, "Se ha actualizado los datos del cliente.", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(this, "Debe ingresar un código válido.", Toast.LENGTH_LONG).show();
        }


    }
}