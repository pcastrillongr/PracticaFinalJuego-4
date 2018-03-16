package com.mygdx.game;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;


public class Opciones extends Activity {



     public static  boolean  music;
    int contador;
    public SharedPreferences pref;
    static Button boton;
    public static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        contador = 0;
        boton = (Button) findViewById(R.id.button);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        if (pref.getBoolean("musica", false)) {

            boton.setText("OFF");}



    }

    //guardamos la preferencia del sonido en un booleano

    public void onClick(View view) {
        contador++;
        if (contador % 2 == 0) {
            editor.putBoolean("musica", false);
            editor.commit();
            boton.setText("OFF");



        } else {
            editor.putBoolean("musica", true);
            editor.commit();
            boton.setText("ON");


        }
        music=pref.getBoolean("musica",true);



    }
}
