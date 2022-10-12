package Clases;
// Análisis, diseño, implementacion, pruebas.

import Utilidades.Validaciones;

public class Empresa {

// Declaración de atributos

    private Inventario Inventario;
    private String Nombre;

// Constructor.

    public Empresa(String nombre) {
        this.Inventario = new Inventario(nombre);
        this.Nombre = nombre;
    }

    public Empresa(Inventario inventario, String nombre) {
        this.Inventario = inventario;
        this.Nombre = nombre;
    }

    // Métodos para la gestión de inventario.

    public void menuGestionInventario() {
        try {
            System.out.println("Empresa [" + this.Nombre + "]\n");
            mostrarOpciones();
            opcionMenu();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            menuGestionInventario();
        }
    }

    private void opcionMenu() {
        boolean quedarse = true;
        switch (Validaciones.validarInt(1, 5)) {
            case 1 -> this.Inventario.agregarObjeto();
            case 2 -> this.Inventario.eliminarObjeto();
            case 3 -> this.Inventario.modificarCantidad();
            case 4 -> this.Inventario.mostrarInventario();
            case 5 -> quedarse = false;
        }
        if (quedarse) menuGestionInventario();
    }

    private void mostrarOpciones() {
        System.out.println("""
                
                [Menú de la empresa, gestión de inventario]
                
                ¿Qué deseas hacer?
                [1] Agregar objeto.
                [2] Eliminar objeto.
                [3] Modificar existencia de objeto.
                [4] Mostrar inventario.
                [5] Salir.
                
                """);
    }

// Getters

    public String getNombre() {
        return Nombre;
    }

// Setters

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


}
