package Objetos;

public class Empresa {

// Declaración de atributos
    private Inventario Inventario;
    private String Nombre;
    private int Fondos;

// Constructor.
    public Empresa(String nombre) {
        this.Inventario = new Inventario();
        this.Nombre = nombre;
    }

// Getters
    public String getNombre() {
        return this.Nombre;
    }

    public Inventario getInventario() {
        return this.Inventario;
    }

    public int getFondos(){
        return this.Fondos;
    }
// Setters
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setFondos(int fondos) {
        this.Fondos = fondos;
    }

}
