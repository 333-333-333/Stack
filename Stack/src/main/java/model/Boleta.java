package model;

import utillities.Validaciones;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Boleta {

    private ArrayList<Objeto> Carro;
    private int Total;
    private String FechaHora;

    /**
     * Constructor del objeto.
     */
    public Boleta() {
        this.Carro = new ArrayList<>();
        this.Total = 0;
    }

    /**
     * Actualiza el total del carro.
     */
    private void actualizarTotal() {
        int total = 0;

        for (Objeto producto : this.Carro) {
            Validaciones.overflowInt(producto.getPrecio());
            Validaciones.overflowInt(total);
            total += producto.getPrecio();
        }

        this.Total = total;
    }

    /**
     * Agrega un objeto al carro
     * @param agregado
     */
    public void agregarAlCarro(Objeto agregado) {
            this.Carro.add(agregado);
    }

    /**
     * Elimina un objeto del carro, si no hay existencia, se arroja una
     * excepción.
     * @param removido
     */
    public void removerDelCarro(Objeto removido) {
        if (this.Carro.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("No hay objetos.");
        }
        if (!this.Carro.contains(removido)) {
            throw new IllegalArgumentException("No está este objeto.");
        }
        this.Carro.remove(removido);
    }


    /**
     * Se actualiza la fecha y hora de la boleta.
     */
    private void actualizarFechaHora() {
        DateFormat formatoFechaHora = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date fechaHora = new Date();

        this.FechaHora = formatoFechaHora.format(fechaHora);

    }

    /**
     * Getters y setters.
     */

    public ArrayList<Objeto> getCarro() {
        return this.Carro;
    }

    public String getFechaHora() {
        actualizarFechaHora();
        return this.FechaHora;
    }

    public int getTotal() {
        return this.Total;
    }

    /**
     * toString.
     * @return
     */
    public String toString() {
        actualizarTotal();
        actualizarFechaHora();
        String boleta = "[Boleta]\n "
                + this.FechaHora + "\n";
        for (Objeto producto : this.Carro) {
            boleta += "\n" + producto.getNombre()
                + "\n($" + producto.getPrecio() + ")";
        }
        boleta += "\nTotal de la compra: " + this.Total
            + "\n¡Gracias por tu compra! <3.";
        return boleta;
    }


}
