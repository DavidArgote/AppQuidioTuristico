package com.senasoft.appquindioturistico.control.control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.senasoft.appquindioturistico.R;
import com.senasoft.appquindioturistico.control.modelo.AdapterItemLista;
import com.senasoft.appquindioturistico.control.modelo.Datos;
import com.senasoft.appquindioturistico.control.modelo.ManagerHelper;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HotelesActivity extends AppCompatActivity {

    private ImageButton btnVolver;
    private ListView lvlista;


    private String categoria = "Hotel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);

        getSupportActionBar().hide();

        referenciar();

        ManagerHelper managerHelper = new ManagerHelper(this);

        final ArrayList<Datos> lista = new ArrayList<>(managerHelper.consultarCategorias(categoria));

        AdapterItemLista adapter = new AdapterItemLista(this, lista);
        lvlista.setAdapter(adapter);

        lvlista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(HotelesActivity.this, DetalleActivity.class);

                intent.putExtra("foto", lista.get(i).getFoto());
                intent.putExtra("nombre", lista.get(i).getNombre());
                intent.putExtra("ubicacion", lista.get(i).getUbicacion());
                intent.putExtra("desc", lista.get(i).getDesc_corta());

                startActivity(intent);

            }
        });

    }

    private void referenciar() {

        btnVolver = findViewById(R.id.btnVolverHoteles);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lvlista = findViewById(R.id.lvListaHoteles);

    }

}
