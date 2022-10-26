package Objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Inventario {

// Declaración de atributos.
    private ArrayList<Objeto> Objetos;

// Constructor
    public Inventario() {
        this.Objetos = new ArrayList<>();
    }

// Método para agregar un objeto al inventario.
    public void agregarObjeto(Objeto objeto) {
        if (objeto == null) {
            throw new NullPointerException("El objeto a agregar es nulo.");
        }
        this.Objetos.add(objeto);
    }

// Método para eliminar objetos.
    public void eliminarObjeto(Objeto objeto) {
        if (this.Objetos.size() == 0)  {
            throw new ArrayIndexOutOfBoundsException("No hay objetos.");
        }
        if (objeto == null) {
            throw new NullPointerException("El objeto es nulo.");
        }
        if (!this.Objetos.contains(objeto)) {
            throw new IllegalArgumentException("El objeto no está presente.");
        }
        this.Objetos.remove(objeto);
    }

// Métodos para ordenar el inventario.
    // De menor a mayor cantidad.
    public void ordenarPorCantidadMasMenos() {
        ordenarPorCantidadMenosMas();
        Collections.reverse(this.Objetos);
    }

    // De mayor a menor cantidad.
    public void ordenarPorCantidadMenosMas() {
        if (this.Objetos.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("No hay objetos.");
        }
        this.Objetos.sort(Comparator.comparing(Objeto::getCantidad));
    }

    // Por nombre, de la Z a la A.
    public void ordenarPorNombreZA() {
        ordenarPorNombreAZ();
        Collections.reverse(this.Objetos);
    }

    // Por nombre, de la A a la Z.
    public void ordenarPorNombreAZ() {
        this.Objetos.sort(Comparator.comparing(Objeto::getNombre));
        if (this.Objetos.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("No hay objetos.");
        }
    }

    public void ordenarPorPrecioMenosMas() {
        this.Objetos.sort(Comparator.comparing(Objeto::getPrecio));
        if (this.Objetos.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("No hay objetos.");
        }
    }

    public void ordenarPorPrecioMasMenos() {
        ordenarPorPrecioMenosMas();
        Collections.reverse(this.Objetos);
    }

// Getters
    public ArrayList<Objeto> getObjetos() {
        return this.Objetos;
    }

    public Objeto getObjeto(int indice) {
        if (indice>=0 && indice<=this.Objetos.size()-1) {
            return this.Objetos.get(indice);
        } else {
            throw new NullPointerException("El índice está fuera de los límites");
        }
    }

// Método para mostrar el inventario.
    public String toString() {
        String inventario = "";
        for (int indiceInventario = 0;
             indiceInventario < this.Objetos.size();
             indiceInventario++) {
            inventario += "\n[" + indiceInventario + "] "
                    + this.Objetos.get(indiceInventario).toString()
                    + "\n";
        }
        return inventario;
    }


}
