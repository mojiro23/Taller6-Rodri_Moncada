package com.example.taller_6_Rodrigo_Moncada_Jimenez.Modelos;

import android.arch.persistence.room.Entity;


@Entity(tableName = "articulo")

public class Articulo {


    private String nombre;
    private String cantidad;

    public Articulo(String nombre, String cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;

    }

    public Articulo() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }


}
