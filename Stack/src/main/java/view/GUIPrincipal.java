package view;

import controller.ControladorPrincipal;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIPrincipal extends GUIFactory {

    private ControladorPrincipal Controlador;
    private JPanel PanelPrincipal;
    private JButton BotonGEmpresa, Salir;
    private JLabel Saludo, Opciones, Datos;

    public GUIPrincipal() {
        super(800, 600);
        this.setVisible(true);
        cargarElementos();
        asignarElementos();
        visibilizarPaneles();
        this.Controlador = new ControladorPrincipal(this);
    }

    protected void cargarBotones() {
        cargarBotonesPanelPrincipal();
    }

    private void cargarBotonesPanelPrincipal() {
        this.BotonGEmpresa = crearJButton(175, 100,
                150, 150,
                "Gestionar empresa");

        this.Salir = crearJButton(375, 100,
                150, 150,
                "Salir");
    }

    @Override
    protected void cargarTextos() {
        this.Saludo = crearTitulo("¡Que gusto tenerte de vuelta en Stack!",
                0, 50,
                800, 200);


        this.Opciones = crearSubtitulo("¿Qué deseas hacer?",
                0, -30,
                700, 200);

        this.Datos = crearSubtitulo("333-333-333, ICC260. ",
                0, 270,
                700, 100);
    }

    @Override
    protected void cargarPaneles() {
        cargarPanelPrincipal();
    }

    private void cargarPanelPrincipal() {
        this.PanelPrincipal = crearPanel(50, 200,
                700, 350);
    }

    @Override
    protected void asignarAFrame() {
        this.add(this.PanelPrincipal);
        this.add(this.Saludo);
    }


    @Override
    protected void asignarAPaneles() {
        asignarAPanelPrincipal();
    }

    private void asignarAPanelPrincipal() {
        this.PanelPrincipal.add(this.BotonGEmpresa);
        this.PanelPrincipal.add(this.Salir);

        this.PanelPrincipal.add(this.Opciones);
        this.PanelPrincipal.add(this.Datos);
    }

    protected void visibilizarPaneles() {
        this.PanelPrincipal.setVisible(true);
    }

    public void setActionListener(ActionListener a) {
        this.BotonGEmpresa.addActionListener(a);
        this.Salir.addActionListener(a);
    }

    public JButton getSalir() {
        return this.Salir;
    }

    public JButton getBotonGEmpresa() {
        return this.BotonGEmpresa;
    }

}

