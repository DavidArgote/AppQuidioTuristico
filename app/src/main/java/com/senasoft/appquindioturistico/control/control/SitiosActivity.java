package com.senasoft.appquindioturistico.control.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.senasoft.appquindioturistico.R;
import com.senasoft.appquindioturistico.control.modelo.AdapterItemLista;
import com.senasoft.appquindioturistico.control.modelo.Datos;
import com.senasoft.appquindioturistico.control.modelo.ManagerHelper;

import java.util.ArrayList;

public class SitiosActivity extends AppCompatActivity {

    private ImageButton btnVolver;
    private ListView lvLista;

    private String categoria = "Sitio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios);

        getSupportActionBar().hide();

        referenciar();

        ManagerHelper managerHelper = new ManagerHelper(this);

        final ArrayList<Datos> lista = new ArrayList<>(managerHelper.consultarCategorias(categoria));

        AdapterItemLista adapter = new AdapterItemLista(this, lista);
        lvLista.setAdapter(adapter);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(SitiosActivity.this, DetalleActivity.class);

                intent.putExtra("foto", lista.get(i).getFoto());
                intent.putExtra("nombre", lista.get(i).getNombre());
                intent.putExtra("ubicacion", lista.get(i).getUbicacion());
                intent.putExtra("desc", lista.get(i).getDesc_corta());

                startActivity(intent);

            }
        });

    }

    private void referenciar() {

        btnVolver = findViewById(R.id.btnVolverSitios);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SitiosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lvLista = findViewById(R.id.lvListaSitios);

    }
}
