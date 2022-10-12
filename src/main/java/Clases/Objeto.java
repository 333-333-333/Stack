package Clases;

public class Objeto {

// Declaración de atributos.
    private String Nombre, Desc, Proveedor;
    private int ID, CantidadCritica, Cantidad;


// Constructor
    public Objeto(String nombre, String proveedor, String desc, int cantidadCritica, int ID) {
        this.Nombre = nombre;
        this.Proveedor = proveedor;
        this.Desc = desc;
        this.CantidadCritica = cantidadCritica;
        this.ID = ID;
        this.Cantidad = 1;
    }

// Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

// Getters
    public int getID() {
        return ID;
    }

    public int getCantidadCritica() {
        return CantidadCritica;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public String getDesc() {
        return Desc;
    }

    public String getNombre() {
        return Nombre;
    }

// toString.
    @Override
    public String toString() {
        return "[" + this.Nombre + "]\n"
                + this.Desc
                + "\n[Proveedor : " + this.Proveedor +"]"
                + "\n[Cantidad disponible = " + this.Cantidad +"]";
    }

}
