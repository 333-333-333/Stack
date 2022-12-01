package view;

import controller.ControladorGestorVentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class GUIGestorVentas extends GUIFactory{

    private ControladorGestorVentas Controlador;
    private ArrayList<JButton> ArregloListaBotones;
    private DefaultListModel ListaBotones;
    private JScrollPane PanelScroll, TextoScroll;
    private JPanel PanelLista, PanelCarro, PanelObjeto;
    private JLabel EncabezadoVentana, CantidadObjeto;
    private JTextPane ListaCarro, DatosObjeto, EncabezadoObjeto;
    private JButton Volver, VolverACarro,
            Añadir, Restar,
            BorrarCompra, FinalizarCompra;


    public GUIGestorVentas(ControladorGestorVentas controlador) {
        super(1280, 720);
        this.Controlador = controlador;
        this.setVisible(true);
        cargarElementos();
        asignarElementos();
        visibilizarPaneles();
    }

    @Override
    protected void cargarPaneles() {
        crearPanelLista();
        crearPanelCarro();
        crearPanelObjeto();
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

    private void crearPanelCarro() {
        this.PanelCarro = crearPanel(420, 90,
                800, 550);
    }

    private void crearPanelObjeto() {
        this.PanelObjeto = crearPanel(420, 90,
                800, 550);
    }

    @Override
    protected void cargarBotones() {
        crearBotonVolver();
        crearBotonVolverACarro();
        crearBotonRestar();
        crearBotonAñadir();
        crearBotonFinalizarCompra();
        crearBotonBorrarCompra();
        crearBotonesObjetos();
    }

    private void crearBotonVolver() {
        this.Volver = crearJButton(145, 25,
                100, 40,
                "Volver");
    }

    private void crearBotonVolverACarro() {
        this.VolverACarro = crearJButton(620, 480,
                150, 50,
                "Volver al carro");
    }
    private void crearBotonRestar() {
        this.Restar = crearJButton(300, 480,
                50, 50,
                "-");
    }

    private void crearBotonAñadir() {
        this.Añadir = crearJButton(450, 480,
                50, 50,
                "+");
    }


    private void crearBotonFinalizarCompra() {
        this.FinalizarCompra = crearJButton(250, 480,
                120, 50,
                "Finalizar compra");
    }

    private void crearBotonBorrarCompra() {
        this.BorrarCompra = crearJButton(425, 480,
                120, 50,
                "Borrar compra");
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

    @Override
    protected void cargarTextos() {
        cargarLabelsVentanas();
    }

    private void cargarLabelsVentanas() {
        crearEncabezadoVentana();
        cargarTextoPanelCarro();
        cargarTextoPanelObjeto();
    }

    private void crearEncabezadoVentana() {
        this.EncabezadoVentana = crearTitulo("Gestor de ventas",
                520, -60,
                600, 200);

    }

    private void cargarTextoPanelCarro() {
        crearListaCarro();
    }

    private void crearListaCarro() {
        this.ListaCarro = crearTextPane("La lista está vacía, agrega objetos",
                100, 50,
                600, 400);

        crearTextoScroll();
    }

    private void crearTextoScroll() {
        this.TextoScroll = new JScrollPane(this.ListaCarro);

        this.TextoScroll.setBounds(100, 50,
                600, 400);

        TextoScroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        TextoScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

        this.ListaCarro.setForeground(Color.black);
        this.TextoScroll.setForeground(Color.black);
    }

    private void cargarTextoPanelObjeto() {
        crearEncabezadoObjeto();
        crearDatosObjeto();
        crearCantidadObjeto();
    }


    private void crearEncabezadoObjeto() {
        this.EncabezadoObjeto = crearTextPane("Agregas un objeto al carro",
                0, 0,
                800, 300);
    }

    private void crearDatosObjeto() {
        this.DatosObjeto = crearTextPane("",
                0, 200,
                800, 300);
    }

    private void crearCantidadObjeto() {
        this.CantidadObjeto = crearTitulo("0",
                0, 480,
                800, 50);
    }


    @Override
    protected void asignarAFrame() {
        this.add(this.EncabezadoVentana);
        this.add(this.Volver);
        this.add(this.PanelScroll);
        this.add(this.PanelCarro);
        this.add(this.PanelObjeto);
    }

    @Override
    protected void asignarAPaneles() {
        asignarAPanelCarro();
        asignarAPanelObjeto();
        asignarAPanelLista();
    }

    private void asignarAPanelCarro() {
        this.PanelCarro.add(this.TextoScroll);
        this.PanelCarro.add(this.FinalizarCompra);
        this.PanelCarro.add(this.BorrarCompra);

    }

    private void asignarAPanelObjeto() {
        this.PanelObjeto.add(this.Restar);
        this.PanelObjeto.add(this.Añadir);
        this.PanelObjeto.add(this.DatosObjeto);
        this.PanelObjeto.add(this.EncabezadoObjeto);
        this.PanelObjeto.add(this.CantidadObjeto);
        this.PanelObjeto.add(this.VolverACarro);
    }

    private void asignarAPanelLista() {
        for (int nBoton = 0; nBoton < this.ListaBotones.size() ; nBoton ++) {
            this.PanelLista.add((JButton) this.ListaBotones.getElementAt(nBoton));
        }
    }

    @Override
    public void visibilizarPaneles() {
        this.PanelLista.setVisible(true);
        visibilizarPanelCarro();
    }

    public void visibilizarPanelCarro() {
        this.PanelCarro.setVisible(true);
        this.PanelObjeto.setVisible(false);
    }

    public void visibilizarPanelObjeto() {
        this.PanelCarro.setVisible(false);
        this.PanelObjeto.setVisible(true);
    }

    public void actualizarListaBotones() {
        this.PanelLista.removeAll();
        crearBotonesObjetos();
        asignarAPaneles();
    }

    @Override
    public void setActionListener(ActionListener listener) {
        this.FinalizarCompra.addActionListener(listener);
        this.BorrarCompra.addActionListener(listener);
        this.Añadir.addActionListener(listener);
        this.Restar.addActionListener(listener);
        this.Volver.addActionListener(listener);
        this.VolverACarro.addActionListener(listener);

        for (JButton boton : this.ArregloListaBotones) {
            boton.addActionListener(listener);
        }
    }

    public ArrayList<JButton> getArregloListaBotones() {
        return this.ArregloListaBotones;
    }

    public JButton getVolver() {
        return this.Volver;
    }

    public JButton getVolverACarro() {
        return this.VolverACarro;
    }

    public JButton getAñadir() {
        return this.Añadir;
    }

    public JButton getRestar() {
        return this.Restar;
    }

    public JButton getBorrarCompra() {
        return this.BorrarCompra;
    }

    public JButton getFinalizarCompra() {
        return this.FinalizarCompra;
    }

    public JTextPane getListaCarro() {
        return this.ListaCarro;
    }

    public JTextPane getDatosObjeto() {
        return this.DatosObjeto;
    }

    public JTextPane getEncabezadoObjeto() {
        return this.EncabezadoObjeto;
    }

    public JLabel getCantidadObjeto() {
        return this.CantidadObjeto;
    }
}
