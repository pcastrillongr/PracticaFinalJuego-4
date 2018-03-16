package com.mygdx.game;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mygdx.game.Database.JuegoDataBase;

/**
 * Created by admincenec on 05/03/2018.
 */

public class JuegoDatabaseOpenHelper extends SQLiteOpenHelper {

    public JuegoDatabaseOpenHelper(Context context)  {
        super(context, JuegoDataBaseAndroid.getDatabaseName(), null, JuegoDataBaseAndroid.getDatabaseVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(JuegoDataBaseAndroid.getDatabaseCreationQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(JuegoDataBaseAndroid.getDatabaseUpdateQuery());
    }
}
