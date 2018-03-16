package com.mygdx.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Inicio extends Activity {


    public static boolean musica;
    ImageView play;
    ImageView exit;
    ImageView settings;
    ImageView record;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
         alertDialog = new AlertDialog.Builder(getApplicationContext()).create();



        setContentView(R.layout.inicio);


        play=(ImageView)findViewById(R.id.imageView);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent go=new Intent(getApplicationContext(),AndroidLauncher.class);
                    startActivity(go);


            }
            });

        exit=(ImageView)findViewById(R.id.imageView3);

        //alert dialog que nos preguntara si deseamos salir del juego
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(Inicio.this, android.R.style.Theme_Holo_Dialog);
                } else {
                    builder = new AlertDialog.Builder(Inicio.this);
                }
                builder.setTitle("Salir del Juego")
                        .setMessage("Estas seguro que deseas salir?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });



        settings=(ImageView)findViewById(R.id.imageView4);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent go=new Intent(getApplicationContext(),Opciones.class);
                startActivity(go);


            }
        });
        record=(ImageView)findViewById(R.id.imageView2);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go=new Intent(getApplicationContext(),Records.class);
                startActivity(go);
            }
        });


    }




}
