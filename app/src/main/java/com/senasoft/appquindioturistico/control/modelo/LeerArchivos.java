package com.senasoft.appquindioturistico.control.modelo;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeerArchivos {

    private Context context;

    public LeerArchivos(Context context) {
        this.context = context;
    }


    public void leerHoteles(){

        String linea = "";
        String categoria = "Hotel";
        String datos[];

        ManagerHelper managerHelper = new ManagerHelper(context);

        File ruta = new File(Environment.getExternalStorageDirectory(), "/Download");
        File archivo = new File(ruta, "Hoteles.csv");

        FileReader reader;

        try{

            reader = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(reader);

            while(buffer.ready()){

                if(!(linea = buffer.readLine()).equals("\000")){

                    Datos datosObj = new Datos();

                    datos = linea.split(";");

//                    Toast.makeText(context, "Datos: " + datos[3], Toast.LENGTH_SHORT).show();

                    datosObj.setNombre(datos[0]);
                    datosObj.setDesc_corta(datos[1]);
                    datosObj.setUbicacion(datos[2]);
                    datosObj.setDesc_larga(datos[3]);
                    datosObj.setCategoria(categoria);

                    long insert = managerHelper.insertDatos(datosObj);

                    if (insert < 0 ){
                        Toast.makeText(context, "No se pudo registrar los datos", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void leerRestaurantes(){

        ManagerHelper managerHelper = new ManagerHelper(context);

        String categoria = "Restaurante";

        String linea = "";
        String[] datos;

        File ruta = new File(Environment.getExternalStorageDirectory(), "/Download");
        File doc = new File(ruta, "Restaurantes.csv");

        FileReader reader;

        try {

            reader = new FileReader(doc);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while (bufferedReader.ready()){

                if (!(linea = bufferedReader.readLine()).equals("\000")){
                    datos = linea.split(";");

                    Datos datosObj = new Datos();

                    datosObj.setNombre(datos[0]);
                    datosObj.setDesc_corta(datos[1]);
                    datosObj.setUbicacion(datos[2]);
                    datosObj.setDesc_larga(datos[3]);
                    datosObj.setCategoria(categoria);

                    long insert = managerHelper.insertDatos(datosObj);

                    if (insert < 0 ){
                        Toast.makeText(context, "No se pudo registrar los datos", Toast.LENGTH_SHORT).show();
                    }

                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void leerSitios(){

        ManagerHelper managerHelper = new ManagerHelper(context);

        String categoria = "Sitio";

        String linea = "";
        String[] datos;

        File ruta = new File(Environment.getExternalStorageDirectory(), "/Download");
        File doc = new File(ruta, "Sitios.csv");

        FileReader reader;

        try {

            reader = new FileReader(doc);

            BufferedReader bufferedReader = new BufferedReader(reader);

            while (bufferedReader.ready()){

                if (!(linea = bufferedReader.readLine()).equals("\000")){

                    datos = linea.split(";");

                    Datos datosObj = new Datos();

                    datosObj.setNombre(datos[0]);
                    datosObj.setDesc_corta(datos[1]);
                    datosObj.setUbicacion(datos[2]);
                    datosObj.setDesc_larga(datos[3]);
                    datosObj.setCategoria(categoria);

                    long in = managerHelper.insertDatos(datosObj);

                    if (in < 0){
                        Toast.makeText(context, "No se insetaron los datos", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        }catch (Exception e){

            e.printStackTrace();

        }


    }


}
