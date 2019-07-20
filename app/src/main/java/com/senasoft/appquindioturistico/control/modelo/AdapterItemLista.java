package com.senasoft.appquindioturistico.control.modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.senasoft.appquindioturistico.R;

import java.util.ArrayList;

public class AdapterItemLista extends BaseAdapter {

    Context context;
    ArrayList<Datos> lista;
    LayoutInflater layoutInflater;

    public AdapterItemLista(Context context, ArrayList<Datos> lista) {
        this.context = context;
        this.lista = lista;
    }


    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imagen;
        TextView textNombre;
        TextView textDescripcion;
        TextView textUbicacion;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = layoutInflater.inflate(R.layout.item_lista, viewGroup, false);

        imagen = itemView.findViewById(R.id.imgFotoSitio);
        textNombre = itemView.findViewById(R.id.textNombre);
        textDescripcion = itemView.findViewById(R.id.textDesc);
        textUbicacion = itemView.findViewById(R.id.textUbicacion);

        if (lista.get(i).getFoto() != null) {
            Bitmap foto = convertBitmap(lista.get(i).getFoto());
            imagen.setImageBitmap(foto);
        }else {
            imagen.setImageResource(R.drawable.icono_galeria);
        }

        textNombre.setText(lista.get(i).getNombre());
        textDescripcion.setText(lista.get(i).getDesc_corta());
        textUbicacion.setText(lista.get(i).getUbicacion());

        return itemView;

    }

    public Bitmap convertBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
