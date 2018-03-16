package com.mygdx.game;

import android.content.SharedPreferences;
import android.graphics.Path;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {





    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref=getApplicationContext().getSharedPreferences("MiPreferencias",MODE_PRIVATE);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();


        initialize(new MainGame(Opciones.music,new JuegoDataBaseAndroid(this)), config);
    }



}