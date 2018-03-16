package com.mygdx.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Records extends Activity {

    JuegoDataBaseAndroid db;
      TextView nevera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        db=new JuegoDataBaseAndroid(this);
        nevera=(TextView)findViewById(R.id.textView2);

        if (db.getTopRecord()==null) {

            this.nevera.setText("No hay ninguna partida jugada aun");

        } else {

            for (int i = 0; i < db.getTopRecord().length; i++) {
                this.nevera.setText(db.getTopRecord()[i].toString());
            }
        }

    }
}
