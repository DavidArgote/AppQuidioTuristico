package com.senasoft.appquindioturistico.control.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlHelper extends SQLiteOpenHelper {
    public ControlHelper(Context context) {
        super(context, Constantes.NAME_BD, null, Constantes.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constantes.CREATE_TABLE_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(Constantes.DROP_TABLE_1);
        onCreate(sqLiteDatabase);

    }
}
