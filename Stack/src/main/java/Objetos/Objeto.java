package Objetos;

public class Objeto {

// Declaración de atributos.
    private String Desc, Nombre,Proveedor;
    private int Cantidad, CantidadCritica, ID, Precio;

// Constructor.
    public Objeto(String nombre, String proveedor, String desc, int cantidadCritica, int precio) {
        // Ints.
        this.Cantidad = 1;
        this.CantidadCritica = cantidadCritica;
        this.Precio = precio;

        // Strings.
        this.Desc = desc;
        this.Nombre = nombre;
        this.Proveedor = proveedor;
    }

// Getters.
    public int getCantidad() {
        return this.Cantidad;
    }

    public int getCantidadCritica() {
        return this.CantidadCritica;
    }

    public int getID() {
        return this.ID;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public int getPrecio() {
        return this.Precio;
    }

// Setters.
    public void setDesc(String desc) {
        this.Desc = desc;
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
        return "\n[" + this.Nombre + "]\n"
                + this.Desc
                + "\n[Proveedor : " + this.Proveedor +"]"
                + "\n[Cantidad disponible = " + this.Cantidad +"]"
                + "\n[Precio = " + this.Precio +"]";
    }

}