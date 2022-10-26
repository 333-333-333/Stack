package Objetos;

import Utilidades.GestorErrores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Boleta {

// Declaración de atributos.
    private ArrayList<Objeto> Carro;
    private int Total;
    private String FechaHora;

// Constructor.
    public Boleta() {
        this.Carro = new ArrayList<>();
        this.Total = 0;
    }

// Métodos.
    // Para calcular el total del carro.
    public void calcularTotal() {
        int total = 0;

        for (Objeto producto : this.Carro) {
            GestorErrores.overflowInt(producto.getPrecio());
            GestorErrores.overflowInt(total);
            total += producto.getPrecio();
        }

        this.Total = total;
    }

// Para gestionar los objetos en el carro.
    // Para agregar un objeto al carro.
    public void agregarAlCarro(Objeto agregado, int cantidad) {
        for (int iteracion = 0; iteracion < cantidad; iteracion ++) {
            this.Carro.add(agregado);
        }
    }

    // Para eliminar los objetos en el carro.
    public void removerDelCarro(Objeto removido) {
        if (this.Carro.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("No hay objetos.");
        }
        if (!this.Carro.contains(removido)) {
            throw new IllegalArgumentException("No está este objeto.");
        }
        this.Carro.remove(removido);
    }

    //  Para imprimir el carro
    public void imprimirCarro() {
        int indiceObjeto = 0;
        for(Objeto producto : this.Carro) {
            System.out.println("[" + indiceObjeto + "]"
                + producto.toString());
            indiceObjeto++;
        }
    }

    // Selecciona un objeto del carro.
    public Objeto seleccionarObjeto(int indice) {
        if (indice > this.Carro.size() || indice < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inválido.");
        }
        return this.Carro.get(indice);
    }

// Relacionados a la fecha y hora de generación de la boleta.
    private String obtenerFechaHora() {
        DateFormat formatoFechaHora = new SimpleDateFormat("yyyy MM dd HH-mm-ss");
        Date fechaHora = new Date();
        return formatoFechaHora.format(fechaHora);
    }

    public void actualizarFechaHora() {
        this.FechaHora = obtenerFechaHora();
    }
// Getters
    public int getCantidadProductos() {
        return this.Carro.size();
    }

    public String getFechaHora() {
        actualizarFechaHora();
        return this.FechaHora;
    }

    public int getTotal() {
        return this.Total;
    }
    // toString.
    public String toString() {
        calcularTotal();
        String boleta = "[Boleta]\n "
                + obtenerFechaHora() + "\n";
        for (Objeto producto : this.Carro) {
            boleta += "\n" + producto.getNombre()
                + "\nPrecio: " + producto.getPrecio();
        }
        boleta += "\nTotal de la compra: " + this.Total
            + "\n¡Gracias por tu compra! <3.";
        return boleta;
    }


}
