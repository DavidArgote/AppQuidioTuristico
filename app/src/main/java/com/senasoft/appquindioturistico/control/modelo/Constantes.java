package com.senasoft.appquindioturistico.control.modelo;

public class Constantes {

    public static String NAME_BD = "AppQuindio.db";
    public static int VERSION_DB = 1;

    public static String NAME_TABLE_ESTABLE = "ESTABLECIMIENTOS";

    public static String NAME_COL_HOTEL_1 = "ID";
    public static String NAME_COL_HOTEL_2 = "FOTO";
    public static String NAME_COL_HOTEL_3 = "NOMBRE";
    public static String NAME_COL_HOTEL_4 = "DESC_CORTA";
    public static String NAME_COL_HOTEL_5 = "UBICACION";
    public static String NAME_COL_HOTEL_6 = "DECSC_LARGA";
    public static String NAME_COL_HOTEL_7 = "CATEGORIA";


    public static String CREATE_TABLE_1 = "CREATE TABLE " + NAME_TABLE_ESTABLE + "( " + NAME_COL_HOTEL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                            NAME_COL_HOTEL_2 + " BLOB," + NAME_COL_HOTEL_3 + " TEXT," + NAME_COL_HOTEL_4 + " TEXT," + NAME_COL_HOTEL_5 + " TEXT," +
                                            NAME_COL_HOTEL_6 + " TEXT," + NAME_COL_HOTEL_7+ " TEXT)";

    public static String DROP_TABLE_1 = "DROP TABLE IF EXISTS " + NAME_TABLE_ESTABLE;

    public static String QUERY_ALL = "SELECT * FROM " + NAME_TABLE_ESTABLE;



}
