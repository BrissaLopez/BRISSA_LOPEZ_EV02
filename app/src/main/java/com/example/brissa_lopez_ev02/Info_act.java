package com.example.brissa_lopez_ev02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class Info_act extends AppCompatActivity {

    private VideoView videoView;
    private String ruta;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act);

        videoView = (VideoView)findViewById(R.id.vv); //llamo al elemento VideoView


        //Asignar nuestro video mp4 al videoview a través de su ruta
        ruta = "android.resource://" + getPackageName() + "/" + R.raw.bancobpm; //obtenemos la ruta del video
        Uri uri = Uri.parse(ruta);

        videoView.setVideoURI(uri);
        videoView.start();



        //Controles del video.
        MediaController media = new MediaController(this);
        videoView.setMediaController(media);


    }

    public void maps(View v){
        i = new Intent(this, Maps_act.class);
        startActivity(i);
    }


}