package model;

import java.util.ArrayList;

public class Objeto {

// Declaración de atributos.
    private String Descripcion, Nombre, Proveedor;
    private int Cantidad, CantidadCritica, Precio;

// Constructor.
    public Objeto(String nombre, String desc, String proveedor,
                  int cantidadCritica, int precio, int cantidad) {
        // Ints.
        this.Cantidad = cantidad;
        this.CantidadCritica = cantidadCritica;
        this.Precio = precio;

        // Strings.
        this.Descripcion = desc;
        this.Nombre = nombre;
        this.Proveedor = proveedor;
    }

    public Objeto() {
        // Ints.
        this.Cantidad = 1;
        this.CantidadCritica = 1;
        this.Precio = 1;

        // Strings.
        this.Descripcion = "";
        this.Nombre = "";
        this.Proveedor = "";
    }

// Getters.
    public int getCantidad() {
        return this.Cantidad;
    }

    public int getCantidadCritica() {
        return this.CantidadCritica;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getDescripcion() {
        return this.Descripcion;
    }

    public String getProveedor() {
        return this.Proveedor;
    }

    public int getPrecio() {
        return this.Precio;
    }

// Setters.
    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setProveedor(String proveedor) {
        this.Proveedor = proveedor;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    public void setCantidadCritica(int cantidadCritica) {

        this.CantidadCritica = cantidadCritica;
    }

    public void setPrecio(int precio) {
        this.Precio = precio;
    }

    // toString.
    @Override
    public String toString() {
        return "[Nombre] : " + this.Nombre + "\n"
                + "[Descripción] : " + this.Descripcion + "\n"
                + "[Disponibles] : " + this.Cantidad + "\n"
                + "[Precio] : " + this.Precio;
    }

}