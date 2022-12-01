package controller;

import javax.swing.*;

public abstract class ControladorFactory {

    /**
     * Método que se utilizará en todos los controladores, para confirmar
     * las respectivas acciones que cometerá el usuario.
     * @param advertencia
     * @return
     */
    protected boolean confirmarAccion(String advertencia) {
        String mensaje = "¿Estás seguro? \n" + advertencia;
        int opcion = JOptionPane.showConfirmDialog(null, mensaje);
        return opcion == 0;
    }


}
