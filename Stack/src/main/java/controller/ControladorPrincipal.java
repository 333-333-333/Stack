package controller;

import view.GUIPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal
        extends ControladorFactory
        implements ActionListener {

    private GUIPrincipal Vista;
    private ControladorGestorEmpresas GestorEmpresas;

    /**
     * Constructor del controlador principal, recibe como parámetro su
     * respectiva GUI.
     * @param vista
     */
    public ControladorPrincipal(GUIPrincipal vista) {
        this.Vista = vista;
        this.GestorEmpresas = new ControladorGestorEmpresas(this);
        this.Vista.setActionListener(this);
    }

    /**
     * Hace visible su GUI.
     */
    public void iniciar(){
        this.Vista.setVisible(true);
    }

    /**
     * Responde a los eventos de los botones de las ventanas.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.Vista.setVisible(false);
        if (e.getSource().equals(this.Vista.getBotonGEmpresa())) {
            this.GestorEmpresas.iniciar();
        } else if (e.getSource().equals(this.Vista.getSalir())) {
            this.Vista.dispose();
        }
    }

}
