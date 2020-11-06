package com.example.brissa_lopez_ev02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Prestamos_act extends AppCompatActivity {
    private Spinner cliente, credito;
    private ArrayList<String> listadoCliente, listadoCredito;
    private TextView text;
    private int saldo;
    private int saldoTotal;
    private int deuda;
    private int tipoCredito;
    private String clienteString;
    private String creditoString;
    private int numCuotas;
    private int cuotaFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos_act);

        cliente = (Spinner)findViewById(R.id.spin1);
        credito = (Spinner)findViewById(R.id.spin2);
        text = (TextView)findViewById(R.id.tv_prest);

        listadoCliente = (ArrayList<String>)getIntent().getSerializableExtra("listadoCliente");
        listadoCredito = (ArrayList<String>)getIntent().getSerializableExtra("listadoCredito");

        ArrayAdapter<String> adaptaCliente = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listadoCliente);
        cliente.setAdapter(adaptaCliente);
        ArrayAdapter<String> adaptaCredito = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listadoCredito);
        credito.setAdapter(adaptaCredito);





    }




    public void saldoClientes(View v){
        clienteString = cliente.getSelectedItem().toString();
        creditoString = credito.getSelectedItem().toString();


        switch (clienteString){
            case "Axel":
                saldo = 750000;
                calcularPrestamo(creditoString, saldo);
                break;

            case "Roxana":
                saldo =900000;
                calcularPrestamo(creditoString, saldo);
                break;

            default:
                text.setText("Ha ocurrido un error. Intente de nuevo.");
        }

        saldoTotal = saldo + tipoCredito;

        text.setText("El saldo final de "+ clienteString+ " es igual a $"+saldoTotal);

    }


    //calcular saldo final
    public void calcularPrestamo(String creditoString, int saldo){


        switch (creditoString){
            case "Crédito Hipotecario":
                tipoCredito =1000000;
                numCuotas = 12;
                break;

            case "Crédito Automotriz":
                tipoCredito = 500000;
                numCuotas = 8;
                break;
            default:
                text.setText("Ha ocurrido un error. Intente de nuevo.");
        }

        deuda = (saldoTotal + tipoCredito);

        cuotaFinal = deuda/numCuotas;



    }

    public void calcularDeudas(View v){

        clienteString = cliente.getSelectedItem().toString();
        creditoString = credito.getSelectedItem().toString();


        switch (clienteString){
            case "Axel":
                saldo = 750000;
                calcularPrestamo(creditoString, saldo);
                break;

            case "Roxana":
                saldo =900000;
                calcularPrestamo(creditoString,saldo);
                break;

            default:
                text.setText("Ha ocurrido un error. Intente de nuevo.");
        }

        saldoTotal = saldo + tipoCredito;

        text.setText("El total a pagar de "+ clienteString+" es $"+ deuda +", con una cantidad de " + numCuotas+ " cuotas, de $"+cuotaFinal+" cada una.");
    }
}