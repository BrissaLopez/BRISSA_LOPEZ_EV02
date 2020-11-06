package com.example.brissa_lopez_ev02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText contrasena;
    private ProgressBar progreso;
    private Button loguear;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.et_login1);
        contrasena = (EditText)findViewById(R.id.et_login2);

        progreso= (ProgressBar)findViewById(R.id.pb);
        loguear = (Button)findViewById(R.id.btn_login);

        progreso.setVisibility(View.INVISIBLE); //la progress bar se hace invisible por defecto
        loguear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                loguearse(nombre, contrasena);
            }
        });



    }


    public void loguearse(EditText nombre, EditText contrasena){

        String nombreString = nombre.getText().toString();
        String contrasenaString = contrasena.getText().toString();

        if("android".equalsIgnoreCase(nombreString) && "123".equalsIgnoreCase(contrasenaString)){

            new Task().execute(); //ejecuto mi tarea asíncrona.

        }else{
            Toast.makeText(this, "Usuario o clave incorrecta. Intente nuevamente",Toast.LENGTH_LONG).show();
        }



    }

    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){

            progreso.setVisibility(View.VISIBLE);
        }

        @Override
        protected java.lang.String doInBackground(java.lang.String... strings) {

            for (int i = 1; i<= 10; i++){

                try {
                    Thread.sleep(500);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) { //finaliza mi tarea asíncrona
            progreso.setVisibility(View.INVISIBLE);
            i= new Intent(getBaseContext(), Home_act.class);
            startActivity(i);
        }
    }




}