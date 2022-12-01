package view;

import controller.ControladorGestorInventario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class GUIGestorInventario extends GUIFactory{

    private ControladorGestorInventario Controlador;
    private ArrayList<JButton> ArregloListaBotones;
    private DefaultListModel ListaBotones;
    private JScrollPane PanelScroll;
    private JPanel PanelDefault, PanelLista, PanelEdicion;
    private JButton CrearObjeto, GuardarObjeto, EliminarObjeto, Volver;
    private JTextPane TextoDefault, TextoEdicion;
    private JLabel  EncabezadoVentana,
            EncabezadoNombre, EncabezadoDescripcion,
            EncabezadoProveedor, EncabezadoPrecio,
            EncabezadoCantidadCritica, EncabezadoCantidad;
    private JTextField CampoNombre, CampoDescripcion,
            CampoProveedor, CampoPrecio,
            CampoCCritica, CampoCantidad;

    public GUIGestorInventario(ControladorGestorInventario controlador) {
        super(1280, 720);
        this.Controlador = controlador;
        this.setVisible(true);
        cargarElementos();
        asignarElementos();
        visibilizarPaneles();
    }
    @Override
    protected void cargarElementos() {
        cargarPaneles();
        cargarBotones();
        cargarTextos();
        cargarTextFields();
    }

    @Override
    public void cargarPaneles() {
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

    @Override
    protected void asignarAFrame() {
        this.add(this.Volver);
        this.add(this.EncabezadoVentana);
        this.add(this.PanelDefault);
        this.add(this.PanelScroll);
        this.add(this.PanelEdicion);
    }

    @Override
    protected void asignarAPaneles() {
        asignarAPanelDefault();
        asignarAPanelEdicion();
        asignarAPanelLista();
    }

    private void asignarAPanelLista() {
        for (int nBoton = 0; nBoton < this.ListaBotones.size() ; nBoton ++) {
            this.PanelLista.add((JButton) this.ListaBotones.getElementAt(nBoton));
        }
    }

    private void asignarAPanelDefault() {
        this.PanelDefault.add(this.TextoDefault);
        this.PanelDefault.add(this.CrearObjeto);
    }

    private void asignarAPanelEdicion() {
        asignarLabelsPanelEdicion();
        asignarBotonesPanelEdicion();
        asignarJTextFieldsPanelEdicion();
    }

    private void asignarLabelsPanelEdicion() {
        this.PanelEdicion.add(this.TextoEdicion);
        this.PanelEdicion.add(this.EncabezadoNombre);
        this.PanelEdicion.add(this.EncabezadoDescripcion);
        this.PanelEdicion.add(this.EncabezadoProveedor);
        this.PanelEdicion.add(this.EncabezadoPrecio);
        this.PanelEdicion.add(this.EncabezadoCantidadCritica);
        this.PanelEdicion.add(this.EncabezadoCantidad);
    }

    private void asignarBotonesPanelEdicion() {
        this.PanelEdicion.add(this.GuardarObjeto);
        this.PanelEdicion.add(this.EliminarObjeto);
    }

    private void asignarJTextFieldsPanelEdicion() {
        this.PanelEdicion.add(this.CampoNombre);
        this.PanelEdicion.add(this.CampoDescripcion);
        this.PanelEdicion.add(this.CampoProveedor);
        this.PanelEdicion.add(this.CampoPrecio);
        this.PanelEdicion.add(this.CampoCCritica);
        this.PanelEdicion.add(this.CampoCantidad);
    }

    @Override
    protected void visibilizarPaneles() {
        this.PanelLista.setVisible(true);
        visibilizarPanelDefault();
    }

    public void visibilizarPanelDefault() {
        this.PanelDefault.setVisible(true);
        this.PanelEdicion.setVisible(false);
    }

    public void visibilizarPanelEdicion() {
        this.PanelDefault.setVisible(false);
        this.PanelEdicion.setVisible(true);
    }

    @Override
    public void setActionListener(ActionListener listener) {
        this.Volver.addActionListener(listener);
        this.CrearObjeto.addActionListener(listener);
        this.GuardarObjeto.addActionListener(listener);
        this.EliminarObjeto.addActionListener(listener);

        for (JButton boton : this.ArregloListaBotones) {
            boton.addActionListener(listener);
        }
    }

    public void setActionListener(JButton boton, ActionListener listener) {
        boton.addActionListener(listener);
    }

    /**
     * Se cargan y crean los botones para la GUI.
     */

    @Override
    protected void cargarBotones() {
        crearBotonVolver();
        crearBotonCrearObjeto();
        crearBotonGuardarObjeto();
        crearBotonEliminarObjeto();
        crearBotonesObjetos();
    }

    private void crearBotonVolver() {
        this.Volver = crearJButton(145, 25,
                100, 40,
                "Volver");
    }

    private void crearBotonCrearObjeto() {
        this.CrearObjeto = crearJButton(355, 480,
                120, 50,
                "Créalo");
    }

    private void crearBotonGuardarObjeto() {
        this.GuardarObjeto = crearJButton(250, 480,
                120, 50,
                "Guardar objeto");
    }

    private void crearBotonEliminarObjeto() {
        this.EliminarObjeto = crearJButton(425, 480,
                120, 50,
                "Eliminar Objeto");
    }

    private void crearBotonesObjetos() {
        this.ListaBotones = new DefaultListModel();
        this.ArregloListaBotones = new ArrayList<>();

        for (String nombre : this.Controlador.obtenerNombresObjetos()) {
            JButton botonEmpresa = crearJButton(nombre);
            this.ArregloListaBotones.add(botonEmpresa);
            this.ListaBotones.addElement(botonEmpresa);
        }
    }

    /**
     * Actualiza la lista de botones.
     */

    public void actualizarListaBotones() {
        this.PanelLista.removeAll();
        crearBotonesObjetos();
        asignarAPaneles();
    }

    /**
     * Se cargan y se crean los labels a la GUI.
     */
    @Override
    public void cargarTextos() {
        cargarLabelsVentana();
        cargarTextoPanelEdicion();
        cargarTextoPanelDefault();
    }

    private void cargarLabelsVentana() {
        crearEncabezadoVentana();
    }

    private void cargarTextoPanelEdicion() {
        crearTextoEdicion();
        crearEncabezadoNombre();
        crearEncabezadoDescripcion();
        crearEncabezadoProveedor();
        crearEncabezadoPrecio();
        crearEncabezadoCantidadCritica();
        crearEncabezadoCantidad();
    }

    private void cargarTextoPanelDefault() {
        crearTextoDefault();
    }

    private void crearEncabezadoVentana() {
        this.EncabezadoVentana = crearTitulo("Gestor de inventario",
                520, -60,
                600, 200);
    }

    private void crearEncabezadoNombre() {
        this.EncabezadoNombre = crearSubtitulo("Nombre:",
                -50, 75,
                600, 200);
    }

    private void crearEncabezadoDescripcion() {
        this.EncabezadoDescripcion = crearSubtitulo("Descripción:",
                250, 75,
                600, 200);
    }

    private void crearEncabezadoProveedor() {
        this.EncabezadoProveedor = crearSubtitulo("Proveedor:",
                -50, 150,
                600,200);
    }

    private void crearEncabezadoPrecio() {
        this.EncabezadoPrecio = crearSubtitulo("Precio:",
                250, 150,
                600, 200);
    }

    private void crearEncabezadoCantidadCritica() {
        this.EncabezadoCantidadCritica = crearSubtitulo("Cantidad crítica:",
                -50, 225,
                600, 200);
    }


    private void crearEncabezadoCantidad() {
        this.EncabezadoCantidad = crearSubtitulo("Cantidad:",
                250, 225,
                600, 200);
    }

    private void crearTextoDefault() {
        this.TextoDefault = crearTextPane("Selecciona un objeto o...",
                0, 150,
                800, 200);
    }

    private void crearTextoEdicion() {
        this.TextoEdicion = crearTextPane("Estas editando un objeto",
                0, 0,
                800, 200);
    }
    /**
     * Se cargan y crean los JTextFrames de la GUI.
     */

    private void cargarTextFields() {
        crearCampoNombre();
        crearCampoDescripcion();
        crearCampoProveedor();
        crearCampoPrecio();
        crearCampoCantidadCritica();
        crearCampoCantidad();
    }

    private void crearCampoNombre() {
        this.CampoNombre = crearJTextField(100, 190,
                300, 30);
    }

    private void crearCampoDescripcion() {
        this.CampoDescripcion = crearJTextField(400, 190,
                300, 30);
    }

    private void crearCampoProveedor() {
        this.CampoProveedor = crearJTextField(100, 265,
                300, 30);
    }

    private void crearCampoPrecio() {
        this.CampoPrecio = crearJTextField(400, 265,
                300, 30);
    }

    private void crearCampoCantidadCritica() {
        this.CampoCCritica = crearJTextField(100, 340,
                300, 30);
    }

    private void crearCampoCantidad() {
        this.CampoCantidad = crearJTextField(400, 340,
                300, 30);
    }


    /**
     * Para reiniciar los textos a predeterminado.
     */


    public void reiniciarTextoEdicion() {
        this.TextoEdicion.setText("Estas editando un objeto");
    }

    public void reiniciarTextoDefault() {
        this.TextoDefault.setText("Selecciona un objeto o...");
    }

    /**
     * Getters pertinentes.
     */

    public ArrayList<JButton> getArregloListaBotones() {
        return this.ArregloListaBotones;
    }

    public JButton getCrearObjeto() {
        return this.CrearObjeto;
    }

    public JButton getGuardarObjeto() {
        return this.GuardarObjeto;
    }

    public JButton getEliminarObjeto() {
        return this.EliminarObjeto;
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

    public JTextField getCampoNombre() {
        return this.CampoNombre;
    }

    public JTextField getCampoDescripcion() {
        return this.CampoDescripcion;
    }

    public JTextField getCampoProveedor() {
        return this.CampoProveedor;
    }

    public JTextField getCampoPrecio() {
        return this.CampoPrecio;
    }

    public JTextField getCampoCCritica() {
        return this.CampoCCritica;
    }

    public JTextField getCampoCantidad() {
        return this.CampoCantidad;
    }

}
