package model;

public class Empresa {

    private Inventario Inventario;
    private String Nombre;
    private int Fondos;

    /**
     * Constructor con atributos "Neutros".
     */
    public Empresa() {
        this.Inventario = new Inventario();
        this.Nombre = "";
        this.Fondos = 0;
    }

    /**
     * Getters y setters.
     */

    public String getNombre() {
        return this.Nombre;
    }

    public Inventario getInventario() {
        return this.Inventario;
    }

    public int getFondos(){
        return this.Fondos;
    }

    public void setFondos(int fondos) {
        this.Fondos = fondos;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setInventario(model.Inventario inventario) {
        this.Inventario = inventario;
    }
}
