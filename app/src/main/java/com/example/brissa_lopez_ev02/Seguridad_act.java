package com.example.brissa_lopez_ev02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad_act extends AppCompatActivity {

    private EditText edit;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_act);

        edit = (EditText)findViewById(R.id.et_seg);
        tv = (TextView)findViewById(R.id.tv_seg);
    }

    // Método para Generar una Key o una llave
    private SecretKeySpec generateKey(String password)throws Exception{

        MessageDigest sha = MessageDigest.getInstance("SHA-256");  // Firma hmac para verificar la integridad de los datos.
        byte[] key = password.getBytes("UTF-8");  //el estandar del password va a ser bajo UTF-8
        key = sha.digest(key);  // pasamos la firma hmac a nuestra cadena de byte.
        SecretKeySpec secretkey = new SecretKeySpec(key, "AES");

        return secretkey;
    }


    // Método para encriptar bajo el algoritmo AES.
    public String Encriptar(String datos, String password) throws Exception {

        if (!edit.getText().toString().isEmpty()) {

            // hago el encriptado de datos
            SecretKeySpec secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES"); // obtengo el algoritmo AES
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);  // Hago el encriptado bajo CIPHER.

            byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes()); // cadena de bytes para su encriptación.
            String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT); // encode a String
            return datosEncriptadosString;

        }

        else {
            // Notificación en el dispositivo.
            Toast.makeText(this, "Debe ingresar una clave", Toast.LENGTH_LONG).show();
            return null;
        }

    }

    // Función Encriptar.

    public void Encriptar(View view)
    {
        try {

            String mensaje = Encriptar(edit.getText().toString(), tv.getText().toString());
            tv.setText(mensaje);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}