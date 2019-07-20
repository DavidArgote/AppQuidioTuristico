package com.senasoft.appquindioturistico.control.control;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.senasoft.appquindioturistico.R;

public class DetalleActivity extends AppCompatActivity {

    private ImageView img;
    private TextView textNombre, textUbicacion, textDesc;
    private ImageButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        referenciar();

        llenarDatos();

    }

    private void referenciar() {

        img = findViewById(R.id.imgDetalle);
        textNombre = findViewById(R.id.textNombreDetalle);
        textUbicacion = findViewById(R.id.textUbicacionDetalle);
        textDesc = findViewById(R.id.textDescDetalle);

        btnVolver = findViewById(R.id.btnVolverDetalle);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void llenarDatos(){

        Bundle datos = this.getIntent().getExtras();

        try {

            if (datos.getByteArray("foto") != null){

                Bitmap fotoBit = convertir(datos.getByteArray("foto"));
                img.setImageBitmap(fotoBit);

            }else{
                img.setImageResource(R.drawable.icono_galeria);
            }

            textNombre.setText(datos.getString("nombre"));
            textUbicacion.setText(datos.getString("ubicacion"));
            textDesc.setText(datos.getString("desc"));


        }catch (Exception e){
            e.printStackTrace();
            img.setImageResource(R.drawable.icono_galeria);
        }


    }

    public Bitmap convertir(byte[] bytes){

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    }

}
