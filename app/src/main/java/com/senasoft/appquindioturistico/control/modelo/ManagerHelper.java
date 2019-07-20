package com.senasoft.appquindioturistico.control.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerHelper {

    SQLiteDatabase db;
    ControlHelper datos;

    public ManagerHelper(Context context) {
        datos = new ControlHelper(context);
    }

    public void readDb(){
        db = datos.getReadableDatabase();
    }

    public void writeDb(){
        db = datos.getWritableDatabase();
    }

    public void closeDb(){
        if(db != null){
            db.close();
        }
    }

    public long insertDatos(Datos datos){

        writeDb();

        ContentValues values = new ContentValues();

        values.put(Constantes.NAME_COL_HOTEL_2, datos.getFoto());
        values.put(Constantes.NAME_COL_HOTEL_3, datos.getNombre());
        values.put(Constantes.NAME_COL_HOTEL_4, datos.getDesc_corta());
        values.put(Constantes.NAME_COL_HOTEL_5, datos.getUbicacion());
        values.put(Constantes.NAME_COL_HOTEL_6, datos.getDesc_larga());
        values.put(Constantes.NAME_COL_HOTEL_7, datos.getCategoria());


        long insert = db.insert(Constantes.NAME_TABLE_ESTABLE, null, values);


        closeDb();

        return insert;

    }

    public List<Datos> consultarDatosParam(String parametro){

        readDb();

        ArrayList<Datos> lista = new ArrayList<>();

        String[] param = {parametro};
        String[] campos = {Constantes.NAME_COL_HOTEL_1,Constantes.NAME_COL_HOTEL_2, Constantes.NAME_COL_HOTEL_3,Constantes.NAME_COL_HOTEL_4,Constantes.NAME_COL_HOTEL_5,Constantes.NAME_COL_HOTEL_6,Constantes.NAME_COL_HOTEL_7,};

        Cursor cursor = db.query(Constantes.NAME_TABLE_ESTABLE, campos, Constantes.NAME_COL_HOTEL_3 + " = ?", param, null, null, null);

        if (cursor.moveToFirst()){
            do {

                Datos datos = new Datos();

                datos.setId(cursor.getInt(0));
                datos.setFoto(cursor.getBlob(1));
                datos.setNombre(cursor.getString(2));
                datos.setDesc_corta(cursor.getString(3));
                datos.setUbicacion(cursor.getString(4));
                datos.setDesc_larga(cursor.getString(5));
                datos.setCategoria(cursor.getString(6));

                lista.add(datos);

            }while(cursor.moveToNext());
        }

        closeDb();

        return lista;

    }

    public List<Datos> consultarCategorias(String parametro){

        readDb();

        ArrayList<Datos> lista = new ArrayList<>();

        String[] param = {parametro};
        String[] campos = {Constantes.NAME_COL_HOTEL_1,Constantes.NAME_COL_HOTEL_2, Constantes.NAME_COL_HOTEL_3,Constantes.NAME_COL_HOTEL_4,Constantes.NAME_COL_HOTEL_5,Constantes.NAME_COL_HOTEL_6,Constantes.NAME_COL_HOTEL_7,};

        Cursor cursor = db.query(Constantes.NAME_TABLE_ESTABLE, campos, Constantes.NAME_COL_HOTEL_7 + " = ?", param, null, null, null);

        if (cursor.moveToFirst()){
            do {

                Datos datos = new Datos();

                datos.setId(cursor.getInt(0));
                datos.setFoto(cursor.getBlob(1));
                datos.setNombre(cursor.getString(2));
                datos.setDesc_corta(cursor.getString(3));
                datos.setUbicacion(cursor.getString(4));
                datos.setDesc_larga(cursor.getString(5));
                datos.setCategoria(cursor.getString(6));

                lista.add(datos);

            }while(cursor.moveToNext());
        }

        closeDb();

        return lista;

    }

    public long updateDatos(Datos datos){

        writeDb();

        String[] param = {String.valueOf(datos.getId())};

        ContentValues values = new ContentValues();

        values.put(Constantes.NAME_COL_HOTEL_2, datos.getFoto());
        values.put(Constantes.NAME_COL_HOTEL_3, datos.getNombre());
        values.put(Constantes.NAME_COL_HOTEL_4, datos.getDesc_corta());
        values.put(Constantes.NAME_COL_HOTEL_5, datos.getUbicacion());
        values.put(Constantes.NAME_COL_HOTEL_6, datos.getDesc_larga());
        values.put(Constantes.NAME_COL_HOTEL_7, datos.getCategoria());

        long updates = db.update(Constantes.NAME_TABLE_ESTABLE, values, Constantes.NAME_COL_HOTEL_1 + "=?", param);

        closeDb();
        return updates;
    }

    public long deleteDatos(String id){

        writeDb();

        long deletes = db.delete(Constantes.NAME_TABLE_ESTABLE, Constantes.NAME_COL_HOTEL_1 + "=?", new String[]{id});

        closeDb();

        return deletes;

    }

}
