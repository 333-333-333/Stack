package view;

import controller.ControladorGestorEmpresas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.*;

public class GUIGestorEmpresas extends GUIFactory {

    private ArrayList<JButton> ArregloListaBotones;
    private DefaultListModel ListaBotones;
    private JScrollPane PanelScroll;
    private ControladorGestorEmpresas Controlador;
    private JButton CrearEmpresa, GuardarEmpresa, EliminarEmpresa,
            GestionarVentas, GestionarInventario, Volver;
    private JTextPane TextoDefault, TextoEdicion;
    private JLabel EncabezadoVentana, EncabezadoNombre, EncabezadoFondos;
    private JPanel PanelDefault, PanelEdicion, PanelLista;
    private JTextField CampoFondos, CampoNombreEmpresa;

    public GUIGestorEmpresas(ControladorGestorEmpresas controlador) {
        super(1280, 720);
        this.Controlador = controlador;
        cargarElementos();
        asignarElementos();
        visibilizarPaneles();
    }

    /**
     * Llama a todos los otros métodos declarados para crear y cargar los
     * elementos de la ventana y sus diversos paneles.
     */

    @Override
    protected void cargarElementos() {
        cargarBotones();
        cargarTextos();
        cargarPaneles();
        cargarTextFields();
    }

    /**
     * Se cargan y crean los labels para la GUI.
     */

    @Override
    public void cargarTextos() {
        crearEncabezadoVentana();
        crearEncabezadoFondos();
        crearEncabezadoNombre();
        crearTextoDefault();
        crearTextoEdicion();
    }

    private void crearEncabezadoVentana() {
        this.EncabezadoVentana = crearTitulo("Gestor de empresas",
                520, -60,
                600, 200);
    }

    private void crearEncabezadoFondos() {
        this.EncabezadoFondos = crearSubtitulo("Fondos:",
                100, 110,
                600, 200);
    }
    private void crearEncabezadoNombre() {
        this.EncabezadoNombre = crearSubtitulo("Nombre:",
                100, 35,
                600, 200);
    }

    private void crearTextoDefault() {
        this.TextoDefault = crearTextPane("Selecciona una empresa, o...",
                0, 150,
                800, 200);
    }

    private void crearTextoEdicion() {
        this.TextoEdicion = crearTextPane("Estas editando una empresa.",
                0, 0,
                800, 200);
    }

    /**
     * Se cargan y crean los botones para la GUI.
     */

    @Override
    public void cargarBotones() {
        crearBotonVolver();
        crearBotonCrearEmpresa();
        crearBotonGuardarEmpresa();
        crearBotonEliminarEmpresa();
        crearBotonGestionarInventario();
        crearBotonGestionarVentas();
        crearBotonesEmpresas();
    }

    private void crearBotonVolver() {
        this.Volver = crearJButton(145, 25,
                100, 40,
                "Volver");
    }

    private void crearBotonCrearEmpresa() {
        this.CrearEmpresa = crearJButton(355, 450,
                120, 50,
                "Créala");
    }

    private void crearBotonGuardarEmpresa() {
        this.GuardarEmpresa = crearJButton(100, 450,
                150, 50,
                "Guardar Empresa");
    }

    private void crearBotonEliminarEmpresa() {
        this.EliminarEmpresa = crearJButton(550, 450,
                150, 50,
                "Eliminar");
    }

    private void crearBotonGestionarInventario() {
        this.GestionarInventario = crearJButton(250, 450,
                150, 50,
                "Gestionar inventario");
    }

    private void crearBotonGestionarVentas() {
        this.GestionarVentas = crearJButton(400, 450,
                150, 50,
                "Gestionar ventas");
    }

    /**
     * Desde el controlador, se obtiene una lista de los nombres de la empresa,
     * y se crea un arreglo de botones, donde cada uno llama a la empresa
     * correspondiente.
     */

    private void crearBotonesEmpresas() {
        try {
            this.ListaBotones = new DefaultListModel();
            this.ArregloListaBotones = new ArrayList<>();

            for (String nombre : this.Controlador.obtenerNombresEmpresas()) {
                JButton botonEmpresa = crearJButton(nombre);
                this.ArregloListaBotones.add(botonEmpresa);
                this.ListaBotones.addElement(botonEmpresa);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Se cargan y crean los páneles para la GUI.
     */
    @Override
    protected void cargarPaneles() {
        crearPanelLista();
        crearPanelDefault();
        crearPanelEdicion();
    }

    private void crearPanelLista() {
        this.PanelLista = crearPanel(50, 90,
                300, 550);
        this.PanelLista.setLayout(new GridLayout(0, 1, 1, 1));

        crearPanelScroll();
    }

    private void crearPanelScroll() {
        JScrollPane panelScroll = new JScrollPane(this.PanelLista);
        panelScroll.setBounds(50, 90,
                300, 550);

        panelScroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        panelScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

        this.PanelScroll = panelScroll;
    }

    private void crearPanelDefault() {
        this.PanelDefault = crearPanel(420, 90,
                800, 550);
    }

    private void crearPanelEdicion() {
        this.PanelEdicion = crearPanel(420, 90,
                800, 550);
    }

    /**
     * Se cargan y crean los TextFields para la GUI.
     */
    protected void cargarTextFields() {
        crearCampoFondos();
        crearCampoNombreEmpresa();
    }

    private void crearCampoNombreEmpresa() {
        this.CampoNombreEmpresa = crearJTextField(250, 150,
                300, 30);
    }

    private void crearCampoFondos() {
        this.CampoFondos = crearJTextField(325, 225,
                150, 30);
    }

    /**
     * Asignar elementos de JFrame al frame y páneles correspondientes.
     */
    @Override
    protected void asignarAFrame() {
        this.add(this.EncabezadoVentana);
        this.add(this.PanelScroll);
        this.add(this.PanelDefault);
        this.add(this.PanelEdicion);
        this.add(this.Volver);
    }

    @Override
    protected void asignarAPaneles() {
        asignarAPanelDefault();
        asignarAPanelEdicion();
        asignarAPanelScroll();
    }

    private void asignarAPanelScroll() {
        for (int nBoton = 0; nBoton < this.ListaBotones.size() ; nBoton ++) {
            this.PanelLista.add((JButton) this.ListaBotones.getElementAt(nBoton));
        }
    }
    private void asignarAPanelDefault() {
        this.PanelDefault.add(this.TextoDefault);
        this.PanelDefault.add(this.CrearEmpresa);
    }

    private void asignarAPanelEdicion() {
        agregarCamposAPanelEdicion();
        agregarBotonesAPanelEdicion();
        agregarLabelsAPanelEdicion();
    }


    private void agregarCamposAPanelEdicion() {
        this.PanelEdicion.add(this.CampoFondos);
        this.PanelEdicion.add(this.CampoNombreEmpresa);
    }

    private void agregarBotonesAPanelEdicion() {
        this.PanelEdicion.add(this.GuardarEmpresa);
        this.PanelEdicion.add(this.EliminarEmpresa);
        this.PanelEdicion.add(this.GestionarInventario);
        this.PanelEdicion.add(this.GestionarVentas);
    }

    private void agregarLabelsAPanelEdicion() {
        this.PanelEdicion.add(this.TextoEdicion);
        this.PanelEdicion.add(this.EncabezadoFondos);
        this.PanelEdicion.add(this.EncabezadoNombre);
    }

    @Override
    protected void visibilizarPaneles() {
        this.PanelLista.setVisible(true);
        visibilizarPanelDefault();
    }

    /**
     * Para poder cambiar la visibilidad de los páneles desde el controlador
     * correspondiente.
     */
    public void visibilizarPanelEdicion() {
        this.PanelDefault.setVisible(false);
        this.PanelEdicion.setVisible(true);
    }
    public void visibilizarPanelDefault() {
        this.PanelDefault.setVisible(true);
        this.PanelEdicion.setVisible(false);
    }

    public void setActionListener(ActionListener listener) {
        this.Volver.addActionListener(listener);
        this.CrearEmpresa.addActionListener(listener);
        this.GuardarEmpresa.addActionListener(listener);
        this.EliminarEmpresa.addActionListener(listener);
        this.GestionarVentas.addActionListener(listener);
        this.GestionarInventario.addActionListener(listener);

        for (JButton boton : this.ArregloListaBotones) {
            boton.addActionListener(listener);
        }
    }

    public void actualizarListaBotones() {
        this.PanelLista.removeAll();
        crearBotonesEmpresas();
        asignarAPaneles();
    }

    public void reiniciarTextoEdicion() {
        this.TextoEdicion.setText("Estás editando una empresa.");
    }

    public void asignarTextoDefault() {
        this.TextoDefault.setText("Selecciona una empresa o...");
    }


    public void setActionListener(JButton boton, ActionListener listener) {
        boton.addActionListener(listener);
    }

    public ArrayList<JButton> getArregloListaBotones() {
        return this.ArregloListaBotones;
    }

    public JButton getCrearEmpresa() {
        return this.CrearEmpresa;
    }

    public JButton getGuardarEmpresa() {
        return this.GuardarEmpresa;
    }

    public JButton getEliminarEmpresa() {
        return this.EliminarEmpresa;
    }

    public JButton getGestionarVentas() {
        return this.GestionarVentas;
    }

    public JButton getGestionarInventario() {
        return this.GestionarInventario;
    }

    public JButton getVolver() {
        return this.Volver;
    }

    public JTextPane getTextoDefault() {
        return this.TextoDefault;
    }

    public JTextPane getTextoEdicion() {
        return this.TextoEdicion;
    }

    public JTextField getCampoFondos() {
        return this.CampoFondos;
    }

    public JTextField getCampoNombreEmpresa() {
        return this.CampoNombreEmpresa;
    }

}
