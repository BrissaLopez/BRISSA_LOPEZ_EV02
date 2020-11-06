package com.example.brissa_lopez_ev02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Home_act extends AppCompatActivity {

    private Intent i;
    private ArrayList<String> cliente;
    private ArrayList<String> credito;

    private ViewFlipper vf;
    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);
        vf = (ViewFlipper)findViewById(R.id.slider);



        for(int i = 0; i< images.length; i++){
            flip_image(images[i]); //recibo mi arreglo de imagenes
        }
    }

    public void flip_image(int i){

        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);

        vf.addView(view);
        vf.setFlipInterval(8000);
        vf.setAutoStart(true);

        //sentido del slider
        vf.setInAnimation(this, android.R.anim.slide_out_right);
        vf.setOutAnimation(this, android.R.anim.slide_in_left);

        cliente = new ArrayList<String>();
        credito = new ArrayList<String>();
        anadeClientes();
        anadeCredito();
    }


    public void prestamosIntercambioValores(View v){

        i = new Intent(this, Prestamos_act.class);
        i.putExtra("listadoCliente", cliente);
        i.putExtra("listadoCredito", credito);
        startActivity(i);
    }

    public void anadeClientes(){
        cliente.add("Axel");
        cliente.add("Roxana");
        cliente.add("Betzabé");
        cliente.add("Matías");
    }

    public void anadeCredito(){
        credito.add("Crédito Hipotecario");
        credito.add("Crédito Automotriz");
    }

    public void goToClientes(View v){
        i = new Intent(this,  Clientes_act.class);
        startActivity(i);

    }
    public void goToSeguridad(View v){
        i = new Intent(this,  Seguridad_act.class);
        startActivity(i);

    }



    public void goToInfo(View v){
        i = new Intent(this,  Info_act.class);
        startActivity(i);
    }
}