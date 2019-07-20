package com.senasoft.appquindioturistico.control.control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.senasoft.appquindioturistico.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInicio, btnSitios, btnHoteles, btnRestaurantes;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        referenciar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 111);

            }

        }


   }

    private void referenciar() {

        btnInicio = findViewById(R.id.btnIrInicio);
        btnInicio.setOnClickListener(this);

        btnSitios = findViewById(R.id.btnIrSitios);
        btnSitios.setOnClickListener(this);

        btnHoteles = findViewById(R.id.btnIrHoteles);
        btnHoteles.setOnClickListener(this);

        btnRestaurantes = findViewById(R.id.btnIrRestaurantes);
        btnRestaurantes.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnIrInicio:
                intent = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(intent);
                break;

            case R.id.btnIrSitios:
                intent = new Intent(MainActivity.this, SitiosActivity.class);
                startActivity(intent);
                break;

            case R.id.btnIrHoteles:
                intent = new Intent(MainActivity.this, HotelesActivity.class);
                startActivity(intent);
                break;

            case R.id.btnIrRestaurantes:
                intent = new Intent(MainActivity.this, RestaurantesActivity.class);
                startActivity(intent);
                break;
        }

    }
}
