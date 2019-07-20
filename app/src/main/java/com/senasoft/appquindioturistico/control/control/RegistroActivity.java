package com.senasoft.appquindioturistico.control.control;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.senasoft.appquindioturistico.R;
import com.senasoft.appquindioturistico.control.modelo.Datos;
import com.senasoft.appquindioturistico.control.modelo.LeerArchivos;
import com.senasoft.appquindioturistico.control.modelo.ManagerHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEliminar, btnActualizar, btnRegistrar;
    private EditText editBuscar, editNombre, editDesc, editUbicacion;
    private ImageButton btnCargar;

    private ImageButton btnBuscar, btnTomarFoto;

    private Spinner spTipo;

    private ListView lvResultados;

    private ImageView imgFoto;

    ManagerHelper managerHelper;

    private int REQUEST_CAPTURE = 1;
    private int idRegistro;

    Bitmap bitmap;
    byte[] biteImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().hide();

        referenciar();

    }

    private void referenciar() {

        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(this);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(this);

        btnRegistrar = findViewById(R.id.btnRegistrarGes);
        btnRegistrar.setOnClickListener(this);

        editBuscar = findViewById(R.id.editBuscarGes);
        editNombre = findViewById(R.id.editNombreGes);
        editDesc = findViewById(R.id.editDescGes);
        editUbicacion = findViewById(R.id.editUbicaGes);

        btnBuscar = findViewById(R.id.btnBuscarGes);
        btnBuscar.setOnClickListener(this);

        btnTomarFoto = findViewById(R.id.btnCargarImaGes);
        btnTomarFoto.setOnClickListener(this);

        btnCargar = findViewById(R.id.btnCargarDatos);
        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarDatos();
            }
        });

        spTipo = findViewById(R.id.spTipoGes);

        lvResultados = findViewById(R.id.lvListaResultados);

        imgFoto = findViewById(R.id.imgCargaGes);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnEliminar:
                delete();
                break;

            case R.id.btnActualizar:
                update();
                break;

            case R.id.btnRegistrarGes:
                registrarDatos();
                break;

            case R.id.btnBuscarGes:
                buscarDatos();
                break;
            case R.id.btnCargarImaGes:
                cargarImagen();
                break;

        }

    }


    private void registrarDatos() {

        if (editNombre.getText().toString().isEmpty()) {

            editNombre.setError("Llena el campo nombre");
            editNombre.requestFocus();

        } else if (editDesc.getText().toString().isEmpty()) {

            editDesc.setError("Llena el campo descripción");
            editDesc.requestFocus();

        } else if (editUbicacion.getText().toString().isEmpty()) {

            editUbicacion.setError("Llena el campo ubicación");
            editUbicacion.requestFocus();

        } else {

            try {

                managerHelper = new ManagerHelper(RegistroActivity.this);

                Datos datos = new Datos();

                datos.setFoto(biteImg);
                datos.setNombre(editNombre.getText().toString());
                datos.setDesc_corta(editDesc.getText().toString());
                datos.setUbicacion(editUbicacion.getText().toString());
                datos.setDesc_larga(editDesc.getText().toString());
                datos.setCategoria(spTipo.getSelectedItem().toString());

                long insert = managerHelper.insertDatos(datos);

                if (insert > 0) {
                    Toast.makeText(this, "Se registro", Toast.LENGTH_SHORT).show();
                    clear();
                } else {
                    Toast.makeText(this, "No se registro, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    private void buscarDatos() {

        managerHelper = new ManagerHelper(RegistroActivity.this);

        ArrayList<Datos> lista = new ArrayList<>(managerHelper.consultarDatosParam(editBuscar.getText().toString()));

        ArrayAdapter adapter = new ArrayAdapter(RegistroActivity.this, android.R.layout.simple_list_item_1, lista);

        lvResultados.setAdapter(adapter);

        llenarCampos(lista);

    }

    private void llenarCampos(final ArrayList<Datos> datos) {

        lvResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int id;

                idRegistro = datos.get(i).getId();
                editNombre.setText(datos.get(i).getNombre());
                editDesc.setText(datos.get(i).getDesc_corta());
                editUbicacion.setText(datos.get(i).getUbicacion());

                if (datos.get(i).getCategoria().equals("Hotel")) {
                    id = 1;
                } else if (datos.get(i).getCategoria().equals("Restaurante")) {
                    id = 2;
                } else if (datos.get(i).getCategoria().equals("Sitio")) {
                    id = 3;
                } else {
                    id = 0;
                }
                spTipo.setSelection(id);

            }
        });

    }

    public void clear() {

        editNombre.setText("");
        editUbicacion.setText("");
        editDesc.setText("");
        editBuscar.setText("");
        spTipo.setSelection(0);


    }

    public void cargarImagen() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = data.getExtras();
        bitmap = (Bitmap) extras.get("data");

        imgFoto.setImageBitmap(bitmap);

        convertirBitmar(bitmap);

    }

    private void convertirBitmar(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        biteImg = stream.toByteArray();

    }

    private void update() {

        managerHelper = new ManagerHelper(RegistroActivity.this);

        if (editNombre.getText().equals("")) {
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            Datos datos = new Datos();

            datos.setId(idRegistro);
            datos.setFoto(biteImg);
            datos.setNombre(editNombre.getText().toString());
            datos.setDesc_corta(editDesc.getText().toString());
            datos.setUbicacion(editUbicacion.getText().toString());
            datos.setDesc_larga(editDesc.getText().toString());
            datos.setCategoria(spTipo.getSelectedItem().toString());

            long updtes = managerHelper.updateDatos(datos);

            if (updtes > 0) {
                Toast.makeText(this, "Se actualizo", Toast.LENGTH_SHORT).show();
                buscarDatos();
                clear();
            } else {
                Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void delete() {

        final String param = String.valueOf(idRegistro);

        AlertDialog.Builder alert = new AlertDialog.Builder(RegistroActivity.this);

        alert.setTitle(R.string.advertencia).setMessage(R.string.advertencia1);

        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                managerHelper.deleteDatos(param);
                clear();
            }

        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }

    public void cargarDatos() {

        try {

            LeerArchivos leerArchivos = new LeerArchivos(this);
            leerArchivos.leerHoteles();
            leerArchivos.leerRestaurantes();
            leerArchivos.leerSitios();

            Toast.makeText(this, "Se insertaron los datos", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "No se insertaron los datos", Toast.LENGTH_SHORT).show();
        }


    }

}
