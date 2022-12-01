package controller;

import model.Inventario;
import model.Objeto;
import utillities.Validaciones;
import view.GUIGestorInventario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorGestorInventario
        extends ControladorFactory
        implements ActionListener {

    private ControladorGestorEmpresas Padre;
    private GUIGestorInventario Vista;
    private Inventario Modelo;
    private Objeto ObjetoModelo;

    /**
     * Constructor del controlador, teniendo como parámetro su respectivo
     * controlador "Padre" (GestorEmpresas).
     * @param principal
     */
    public ControladorGestorInventario(ControladorGestorEmpresas principal) {
        this.Padre = principal;
    }

    /**
     * Inicializa la GUI correspondiente al controlador.
     */
    public void iniciar() {
        this.Vista = new GUIGestorInventario(this);
        this.Vista.setActionListener(this);
        asignarTextoDefault();
    }

    /**
     * Listeners para todos los botones de la GUI, a excepción de los de
     * la lista de botones (A los cuales se llama a través de su propio
     * método)
     * @param a the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent a) {
        try {
            if (a.getSource().equals(this.Vista.getVolver())) {
                volverAPadre();
            } else if (a.getSource().equals(this.Vista.getCrearObjeto())) {
                edicionNuevoObjeto();
            } else if (a.getSource().equals(this.Vista.getGuardarObjeto())) {
                generarObjeto();
            } else if (a.getSource().equals(this.Vista.getEliminarObjeto())) {
                eliminarObjeto();
            } else  {
                listenerBotonesEmpresas(a);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            actualizarListenersBotonesEmpresas();
            asignarTextoDefault();
        }
    }

    /**
     * Por temas de limpieza de código, los listeners de la lista de los botones
     * están en este método.
     * @param a
     */
    private void listenerBotonesEmpresas(ActionEvent a){
        for (JButton boton : this.Vista.getArregloListaBotones()) {
            if (a.getSource() == boton) {
                editarObjeto(boton.getText());
                this.Vista.actualizarListaBotones();
            }
        }
    }

    /**
     * Para cada botón de la lista de botones de la gui, se establece
     * su ActionListener como el de esta clase.
     */
    private void actualizarListenersBotonesEmpresas() {
        for (JButton boton : this.Vista.getArregloListaBotones()) {
            this.Vista.setActionListener(boton, this);
        }
    }

    /**
     * Elimina toda las existencias de los objetos iguales al objeto modelo
     * en el arreglo de objetos en el inventario (En caso que exista).
     */
    private void eliminarObjeto() {
        for (Objeto o : this.Modelo.getObjetos()) {
            if (o.getNombre().equals(this.Vista.getCampoNombre().getText())) {
                if (confirmarAccion("La eliminación es irreversible")) {
                    this.Modelo.eliminarObjeto(o);
                    actualizarParametrosGUI();
                    volverADefault();
                } else {
                    String mensaje = "El objeto no se eliminará";
                    this.Vista.getTextoEdicion().setText(mensaje);
                }
            } else {
                String mensaje = "No hay ningún objeto con el mismo nombre";
                this.Vista.getTextoEdicion().setText(mensaje);
            }
        }
    }

    /**
     * Se establece el objeto modelo en uno nuevo, con parámetros neutros,
     * posteriormente, se edita este mismo.
     */
    private void edicionNuevoObjeto() {
        setObjetoModelo(new Objeto());
        editarObjeto();
    }

    /**
     * Se visibiliza el panel de editar objeto.
     */
    private void editarObjeto() {
        rellenarTextFieldsDatosObjetoModelo();
        this.Vista.visibilizarPanelEdicion();
    }

    /**
     * Dada una String con el nombre de un potencial objeto. se busca en
     * la lista de objetos del inventario modelo. Una ves seleccionado, se
     * visibiliza el panel de editar objeto.
     * @param nombre
     */
    private void editarObjeto(String nombre) {
        for (Objeto o : this.Modelo.getObjetos()) {
            if (o.getNombre().equals(nombre)) {
                setObjetoModelo(o);
            }
        }
        editarObjeto();
    }

    /**
     * Toma los parámetros ingresados en el textfield, y actualiza
     * el objeto modelo con estos datos.
     */
    private void generarObjeto() {
        try {
            String nombre = this.Vista.getCampoNombre().getText();
            String descripcion = this.Vista.getCampoDescripcion().getText();
            String proveedor = this.Vista.getCampoProveedor().getText();
            String precioString = this.Vista.getCampoPrecio().getText();
            String cCriticaString = this.Vista.getCampoCCritica().getText();
            String cantidadString = this.Vista.getCampoCantidad().getText();

            controlarCampos(nombre, descripcion,
                    proveedor, precioString,
                    cCriticaString, cantidadString);

            int precio = Integer.parseInt(precioString);
            int cCritica = Integer.parseInt(cCriticaString);
            int cantidad = Integer.parseInt(cantidadString);

            actualizarObjeto(nombre, descripcion, proveedor,
                    precio, cCritica, cantidad);
            guardarObjeto();

            actualizarParametrosGUI();
            volverADefault();
        } catch (Exception e) {
            mostrarError(e, this.Vista.getTextoEdicion());
        }
    }

    /**
     * Actualiza los datos del objeto modelo dados parámetros de
     * referencia.
     * @param nombre
     * @param descripcion
     * @param proveedor
     * @param precio
     * @param cantidadCritica
     * @param cantidad
     */
    private void actualizarObjeto(String nombre,
                                  String descripcion,
                                  String proveedor,
                                  int precio,
                                  int cantidadCritica,
                                  int cantidad) {
        this.ObjetoModelo = new Objeto(nombre, descripcion, proveedor,
                precio, cantidadCritica, cantidad);

    }

    /**
     * Variante de método para guardar el objeto, primero confirma si
     * se desea sobreeescribir (En caso de que haya otro objeto con el mismo
     * nombre), si se confirma, se sobreescriben los objetos del mismo nombre.
     * @throws Exception
     */
    private void guardarObjeto() throws Exception {
        if (obtenerNombresObjetos().contains(this.ObjetoModelo.getNombre())) {
            if (!confirmarAccion("Sobreescribirás el objeto")) {
                throw new Exception("No se sobreescribirá el objeto");
            } else {
                this.Modelo.sobreEscribirObjetos(this.ObjetoModelo);
            }
        } else {
            this.Modelo.agregarObjeto(this.ObjetoModelo);
        }
        this.Padre.guardarEmpresa();
    }

    /**
     * Ejecuta en conjunto todos los métodos para controlar campos.
     * @param nombre
     * @param descripcion
     * @param proveedor
     * @param precioString
     * @param cantidadCriticaString
     * @param cantidadString
     * @throws Exception
     */
    private void controlarCampos(String nombre,
                                 String descripcion,
                                 String proveedor,
                                 String precioString,
                                 String cantidadCriticaString,
                                 String cantidadString) throws Exception {
        String error = "";

        error = controlarCampoNombre(nombre, error);
        error = controlarCampoDescripcion(descripcion, error);
        error = controlarCampoProveedor(proveedor, error);
        error = controlarCampoPrecio(precioString, error);
        error = controlarCampoCantidadCritica(cantidadCriticaString, error);
        error = controlarCampoCantidad(cantidadString, error);

        if (error.length() != 0) throw new Exception(error);
    }

    /**
     * Si la String es nula, o si está sobre un largo específico, se
     * reinicia el campo al default, o se establece su respectivo valor igual
     * al del objeto modelo.
     * @param nombre
     * @param error
     * @return
     */
    private String controlarCampoNombre(String nombre, String error) {
        try {
            Validaciones.validarString(nombre);
            return error;
        } catch (Exception e) {
            String nombreModelo = this.ObjetoModelo.getNombre();
            this.Vista.getCampoNombre().setText(nombreModelo);
            return error + "[Nombre] " + e.getMessage() + "\n";
        }
    }

    /**
     * Si la String es nula, o si está sobre un largo específico, se
     * reinicia el campo al default, o se establece su respectivo valor igual
     * al del objeto modelo.
     * @param descripcion
     * @param error
     * @return
     */
    private String controlarCampoDescripcion(String descripcion, String error) {
        try {
            Validaciones.validarString(descripcion);
            return error;
        } catch (Exception e) {
            String descripcionModelo = this.ObjetoModelo.getDescripcion();
            this.Vista.getCampoDescripcion().setText(descripcionModelo);
            return error + "[Descripcion] " + e.getMessage() + "\n";
        }
    }

    /**

     * @param proveedor
     * @param error
     * @return
     */
    private String controlarCampoProveedor(String proveedor, String error) {
        try {
            Validaciones.validarString(proveedor);
            return error;
        } catch (Exception e) {
            String proveedorModelo = this.ObjetoModelo.getProveedor();
            this.Vista.getCampoDescripcion().setText(proveedorModelo);
            return error + "[Proveedor] " + e.getMessage() + "\n";
        }
    }

    /**
     * Si la cantidad no es positiva, se reinicia a un campo default o
     * se establecen los valores iguales al del objeto modelo.
     * @param precioString
     * @param error
     * @return
     */
    private String controlarCampoPrecio(String precioString,
                                        String error) {
        try {
            int precio = Validaciones.stringAInt(precioString);
            Validaciones.validarPositivo(precio);
            return error;
        } catch (Exception e) {
            int precioModelo = this.ObjetoModelo.getPrecio();
            String precioModeloStr = String.valueOf(precioModelo);
            this.Vista.getCampoPrecio().setText(precioModeloStr);
            return error + "[Precio] " + e.getMessage() + "\n";
        }
    }

    /**
     * Si la cantidad no es positiva, se reinicia a un campo default o
     * se establecen los valores iguales al del objeto modelo.
     * @param cantidadCriticaString
     * @param error
     * @return
     */
    private String controlarCampoCantidadCritica(String cantidadCriticaString,
                                        String error) {
        try {
            int cCritica = Validaciones.stringAInt(cantidadCriticaString);
            Validaciones.validarPositivo(cCritica);
            return error;
        } catch (Exception e) {
            int cCriticaModelo = this.ObjetoModelo.getCantidadCritica();
            String cCriticaModeloStr = String.valueOf(cCriticaModelo);
            this.Vista.getCampoCCritica().setText(cCriticaModeloStr);
            return error + "[Cantidad crítica] " + e.getMessage() + "\n";
        }
    }

    /**
     * Si la cantidad no es positiva, se reinicia a un campo default o
     * se establecen los valores iguales al del objeto modelo.
     * @param cantidadString
     * @param error
     * @return
     */
    private String controlarCampoCantidad(String cantidadString, String error) {
        try {
            int cantidad = Validaciones.stringAInt(cantidadString);
            Validaciones.validarPositivo(cantidad);
            return error;
        } catch (Exception e) {
            int cantidadModelo = this.ObjetoModelo.getCantidad();
            String cantidadModeloStr = String.valueOf(cantidadModelo);
            this.Vista.getCampoCantidad().setText(cantidadModeloStr);
            return error + "[Cantidad] " + e.getMessage() + "\n";
        }
    }

    /**
     * En base a los parámetros del objeto modelo, se rellenan los TextFields
     * para cada campo correspondiente.
     */
    private void rellenarTextFieldsDatosObjetoModelo() {
        Objeto o = this.ObjetoModelo;

        this.Vista.getCampoNombre().setText(o.getNombre());
        this.Vista.getCampoDescripcion().setText(o.getDescripcion());
        this.Vista.getCampoProveedor().setText(o.getProveedor());

        String precioStr = String.valueOf(o.getPrecio());
        this.Vista.getCampoPrecio().setText(precioStr);

        String cantidadCriticaStr = String.valueOf(o.getCantidadCritica());
        this.Vista.getCampoCCritica().setText(cantidadCriticaStr);

        String cantidadStr = String.valueOf(o.getCantidad());
        this.Vista.getCampoCantidad().setText(cantidadStr);
    }

    /**
     * En base a la cantidad de objetos en el inventario, se asigna una String
     * al panel de texto default.
     */
    private void asignarTextoDefault() {
        if (obtenerNombresObjetos().size() == 0) {
            this.Vista.getTextoDefault().setText("No hay objetos");
        } else {
            this.Vista.getTextoDefault().setText("Selecciona un objeto o...");
        }
    }

    /**
     * Vuelve a la ventana "padre", es decir, a la de gestor de empresas.
     * @throws Exception
     */
    private void volverAPadre() throws Exception {
        if (confirmarAccion("No haran efecto cambios sin guardar")) {
            this.Vista.dispose();
            this.Padre.iniciar();
        }
    }

    private void mostrarError(Exception e, JTextPane j) {
        j.setText(e.getMessage());
    }

    /**
     * Dada la lista de objetos en el inventario modelo, se crea un arreglo de
     * Strings, por cada objeto en la lista de objetos, se extrae su nombre y
     * se agrega a la lista, posteriormente, se retorna la lista de Strings.
     * @return
     */
    public ArrayList<String> obtenerNombresObjetos() {
        ArrayList<String> nombresObjetos = new ArrayList<>();
        for (Objeto objeto : this.Modelo.getObjetos()) {
            nombresObjetos.add(objeto.getNombre());
        }

        return nombresObjetos;
    }

    /**
     * Actualiza los parámetros enseñados en la GUI.
     */
    private void actualizarParametrosGUI() {
        if (this.Modelo.getObjetos().size() == 0) {
            this.Vista.getTextoDefault().setText("No hay objetos.");
        } else {
            this.Vista.reiniciarTextoDefault();
        }
        this.Vista.actualizarListaBotones();
        this.Vista.reiniciarTextoEdicion();
    }

    /**
     * Dentro de la ventana, se vuelve al panel defaul.
     */
    private void volverADefault() {
        this.Vista.visibilizarPanelDefault();
        setObjetoModelo(null);
    }

    /**
     * Getters y setters
     */
    private void setObjetoModelo(Objeto o) {
        this.ObjetoModelo = o;
    }

    public void setModelo(Inventario modelo) {
        this.Modelo = modelo;
    }

    public Inventario getModelo() {
        return this.Modelo;
    }

}