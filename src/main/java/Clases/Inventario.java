package Clases;

import Utilidades.Validaciones;
import java.util.ArrayList;

public class Inventario {

// Declaración de atributos.
    private ArrayList<Objeto> Inventario;
    private String Nombre;

// Constructores
    public Inventario() {
        this.Inventario = new ArrayList<>();
    }

    public Inventario(String nombre) {
        this.Inventario = new ArrayList<>();
        this.Nombre = nombre;
    }

// Método para agregar un objeto al inventario.
    public void agregarObjeto() {
        this.Inventario.add(crearObjeto());
    }

// Método para crear objetos.
    private Objeto crearObjeto() {
        String nombre = crearNombre();
        String descripcion = crearDescripcion();
        String proveedor = crearProveedor();
        int cantidadCritica = establecerCantidadCritica();
        int ID = generarID();

        return new Objeto(nombre,
                          proveedor,
                          descripcion,
                          cantidadCritica,
                          ID);
    }

    // Sub métodos para parametrizar los valores que crearán al objeto.
    private String crearNombre() {
        System.out.println("¿Qué nombre tiene el objeto?");
        String nombre = Validaciones.validarString();
        while (tieneNombre(nombre)) {
            System.out.println("El nombre ya existe en el inventario.");
            nombre = Validaciones.validarString();
        }
        return nombre;
    }

    private String crearDescripcion() {
        System.out.println("Inserte una descripción [Si no la deseas, sálta este apartado].");
        return Validaciones.validarString();
    }

    private String crearProveedor() {
        System.out.println("Inserte el proveedor del objeto.");
        String proveedor = Validaciones.validarString();
        while (proveedor.equals("")) {
            System.out.println("No has insertado un proveedor.");
            proveedor = Validaciones.validarString();
        }
        return proveedor;
    }

    private int establecerCantidadCritica() {
        System.out.println("Inserte la cantidad crítica del objeto.");
        return Validaciones.validarPositivo();
    }

    private int generarID() {
        int ID =  (int) (Math.random() * 100000000);
        if (!tieneID(ID)) return ID;
        else return generarID();
    }

    // Método para eliminar objetos.
    public void eliminarObjeto() {
        if (this.Inventario.size() == 0)  {
            throw new NullPointerException("No hay objetos en el inventario");
        }
        Objeto aEliminar = seleccionarObjeto();
        this.Inventario.remove(aEliminar);
    }

// Interfaz para los métodos que modifican la cantidad.
    public void modificarCantidad() {
        if (this.Inventario.size()!=0) {
            Objeto aModificar = seleccionarObjeto();
            int cantidadObjeto = aModificar.getCantidad();
            menuModificarCantidad();
            opcionModificarCantidad(aModificar);
        } else {
            System.err.println("[Error] "
            + "\nEl inventario está vacío.");
        }
    }

    private void menuModificarCantidad() {
        System.out.println("""
                ¿Qué deseas hacerle al objeto?
                [1] Aumentar en ...
                [2] Reducir en ...
                [3] Establecer cantidad en ...
                [4] Volver.
                """);
    }

    private void opcionModificarCantidad(Objeto aModificar) {
        switch (Validaciones.validarInt(1, 3)) {
            case 1 -> incrementar(aModificar);
            case 2 -> decrementar(aModificar);
            case 3 -> establecerCantidad(aModificar);
            case 4 -> System.out.println("Volviendo...");
        }
    }

// Métodos para modificar la cantidad.
    private void incrementar(Objeto objeto) {
        System.out.println("¿En qué cantidad deseas incrementar el objeto?");
        int aumento = Validaciones.validarPositivo();
        for (Objeto analizado : this.Inventario) {
            if (objeto == analizado) {
                analizado.setID(analizado.getCantidad() + aumento);
            }
        }
    }

    private void decrementar(Objeto objeto) {
        System.out.println("¿En qué cantidad deseas decrementar el objeto?");
        int decremento = Validaciones.validarInt(1, objeto.getCantidad()-1);
        for (Objeto analizado : this.Inventario) {
            if (objeto == analizado) {
                if (analizado.getCantidad() <= analizado.getCantidadCritica()) {
                    System.err.println("Ten cuidado, la cantidad es crítica.");
                }
                if (analizado.getCantidad()>1) {
                    analizado.setCantidad(analizado.getCantidad() - decremento);
                } else {
                    System.err.println("No se redujo la cantidad del objeto.");
                }
            }
        }
    }

    private void establecerCantidad(Objeto objeto) {
        System.out.println("¿En qué cantidad deseas establecer el objeto?");
        int cantidad = Validaciones.validarPositivo();
        objeto.setCantidad(cantidad);
    }

// Métodos para validar la existencia de parámetros repetidos en el inventario.
    public boolean tieneID(int ID) {
        for (Objeto objeto : this.Inventario) {
            if (objeto.getID() == ID) return true;
        }
        return false;
    }

    public boolean tieneNombre(String nombre) {
        for (Objeto objeto : this.Inventario) {
            if (objeto.getNombre().equals(Nombre)) return true;
        }
        return false;
    }

// Método para seleccionar un objeto del inventario.
    private Objeto seleccionarObjeto() {
        mostrarInventario();
        System.out.println("Escriba el índice del objeto deseado.");
        int indiceObjeto = Validaciones.validarInt(0, this.Inventario.size());
        return this.Inventario.get(indiceObjeto);
    }

// Método para mostrar el inventario.
    public void mostrarInventario() {
        String inventario = "";
        for (int indiceInventario = 0;
             indiceInventario < this.Inventario.size();
             indiceInventario++) {
             inventario += "[" + indiceInventario + "] "
                    + this.Inventario.get(indiceInventario).toString()
                    + "\n";
        }
        System.out.println(inventario);
    }

// Setters
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

}
