package com.senasoft.appquindioturistico.control.modelo;

public class Datos {

    private int id;
    private byte[] foto;
    private String nombre;
    private String desc_corta;
    private String ubicacion;
    private String desc_larga;
    private String categoria;

    public Datos() {
    }

    public Datos(int id, byte[] foto, String nombre, String desc_corta, String ubicacion, String desc_larga, String categoria) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.desc_corta = desc_corta;
        this.ubicacion = ubicacion;
        this.desc_larga = desc_larga;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc_corta() {
        return desc_corta;
    }

    public void setDesc_corta(String desc_corta) {
        this.desc_corta = desc_corta;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDesc_larga() {
        return desc_larga;
    }

    public void setDesc_larga(String desc_larga) {
        this.desc_larga = desc_larga;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre + "\n" + ubicacion;
    }
}
