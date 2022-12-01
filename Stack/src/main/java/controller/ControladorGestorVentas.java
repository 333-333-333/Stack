package controller;

import model.Boleta;
import model.Inventario;
import model.Objeto;
import utillities.GestorArchivos;
import utillities.Validaciones;
import view.GUIGestorVentas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorGestorVentas
        extends ControladorFactory
        implements ActionListener {

    private Inventario InventarioModelo;
    private Objeto ObjetoModelo;
    private Boleta Modelo;
    private ControladorGestorEmpresas Padre;
    private GUIGestorVentas Vista;

    /**
     * Constructor del controlador
     * @param principal
     */
    public ControladorGestorVentas(ControladorGestorEmpresas principal) {
        this.Padre = principal;
        this.Modelo = new Boleta();
    }

    /**
     * Inicia la ventana asociada directamente al controlador.
     */
    public void iniciar() {
        this.Vista = new GUIGestorVentas(this);
        this.Vista.setActionListener(this);
    }

    /**
     * Por temas de limpieza, el actionListener será ejecutado
     * desde el controlador.
     * @param a the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent a) {
        try {
            if (a.getSource().equals(this.Vista.getVolver())) {
                volverAPadre();
            } else if (a.getSource().equals(this.Vista.getVolverACarro())){
                volverACarro();
            } else if (a.getSource().equals(this.Vista.getAñadir())) {
                añadirObjeto();
            } else if (a.getSource().equals(this.Vista.getRestar())) {
                restarObjeto();
            } else if (a.getSource().equals(this.Vista.getBorrarCompra())) {
                volverAPadre();
            } else if (a.getSource().equals(this.Vista.getFinalizarCompra())) {
                guardarBoleta();
            } else {
                listenerBotonesEmpresas(a);
            }
        } catch (Exception e) {
            this.Vista.getEncabezadoObjeto().setText(e.getMessage());
        } finally {
            actualizarCarro();
        }
    }

    /**
     *  Por temas de simplificar el código, los listeners de los botones de los
     *  objetos se apartan en este método.
     * @param a
     * @throws Exception
     */
    private void listenerBotonesEmpresas(ActionEvent a) throws Exception {
        for (JButton boton : this.Vista.getArregloListaBotones()) {
            if (a.getSource() == boton) {
                cargarObjetoInventario(boton.getText());
                modificarCantidad();
            }
        }
    }

    /**
     * Método par volver a la ventana padre, la del gestor de empresa.
     */
    private void volverAPadre(){
        if (confirmarAccion("No haran efecto cambios sin guardar")) {
            this.Vista.dispose();
            this.Padre.iniciar();
        }
    }

    /**
     * Método para regresar al panel de carro dentro de la vista.
     */
    private void volverACarro() {
        this.Vista.visibilizarPanelCarro();
    }

    /**
     * Dada una string con el nombre de un potencial objeto, el método
     * itera dentro del arreglo de objetos en el inventario, si obtiene
     * una coincidencia, establece el objeto modelo en esta, si no,
     * arroja una excepción.
     * @param nombre
     * @throws Exception
     */
    private void cargarObjetoInventario(String nombre) throws Exception {
        for (Objeto o : this.InventarioModelo.getObjetos()) {
            if (o.getNombre().equals(nombre)) {
                setObjetoModelo(o);
                return;
            }
        }
        throw new Exception("Este objeto no está en el inventario");
    }

    /**
     * Dentro de la vista, cambia el panel al de modificar cantidad.
     */
    private void modificarCantidad() {
        this.Vista.visibilizarPanelObjeto();
        this.Vista.getDatosObjeto().setText(this.ObjetoModelo.toString());

        int enCarroObjetoModelo = cantidadObjetoEnCarro(this.ObjetoModelo);
        String enCarroObjetoModeloString = String.valueOf(enCarroObjetoModelo);
        this.Vista.getCantidadObjeto().setText(enCarroObjetoModeloString);
    }

    /**
     * Enfoca el objeto modelo, y añade uno de estos a la lista de objetos
     * del modelo (Carro), siempre y cuando la cantidad comprable sea positiva.
     */
    private void añadirObjeto() {
        int cantidadObjetoModelo = this.ObjetoModelo.getCantidad();
        int cCriticaObjetoModelo = this.ObjetoModelo.getCantidadCritica();
        int cantidadMaxima = cantidadObjetoModelo - cCriticaObjetoModelo;
        try {
            String cantidadString = this.Vista.getCantidadObjeto().getText();
            int cantidad = Validaciones.stringAInt(cantidadString);

            if (cantidadMaxima - cantidad <= 0) {
                throw new Exception("No puedes añadir más de este objeto");
            } else {
                this.Modelo.agregarAlCarro(this.ObjetoModelo);

                String cantidadNueva = String.valueOf(cantidad + 1);
                this.Vista.getCantidadObjeto().setText(cantidadNueva);
            }
        } catch (Exception e) {
            this.Vista.getEncabezadoObjeto().setText(e.getMessage());
        }
    }

    /**
     * Enfoca el objeto modelo, y elimina uno de estos de la lista de objetos
     * del modelo (Carro), siempre y cuando la cantidad en esta sea mayor
     * estricta a 0.
     */
    private void restarObjeto() {
        try {
            String cantidadString = this.Vista.getCantidadObjeto().getText();
            int cantidad = Validaciones.stringAInt(cantidadString);

            if (cantidad <= 0) {
                throw new Exception("No puedes quitar más de este objeto");
            } else {
                this.Modelo.removerDelCarro(this.ObjetoModelo);

                String cantidadNueva = String.valueOf(cantidad - 1);
                this.Vista.getCantidadObjeto().setText(cantidadNueva);
            }

        } catch (Exception e) {
            this.Vista.getEncabezadoObjeto().setText(e.getMessage());
        }
    }

    /**
     * Guarda la boleta en un .txt
     * @throws Exception
     */
    private void guardarBoleta() throws Exception {
        String boletaString = this.Vista.getListaCarro().getText();
        String ruta = "Boletas/" + this.Modelo.getFechaHora() + ".txt";
        GestorArchivos.crearArchivo(ruta, boletaString);
        actualizarInventario();
        actualizarEmpresa();
        this.Padre.guardarEmpresa();
        volverAPadre();
    }

    /**
     * Se enfoca en el inventario modelo, por cada objeto en el arreglo
     * de objetos, se extrae su nombre, y se suma a un arreglo de Strings
     * de los nombres de los objetos. Posteriormente, se retorna el
     * arreglo.
     * @return
     */
    public ArrayList<String> obtenerNombresObjetos() {
        ArrayList<String> nombresObjetos = new ArrayList<>();
        for (Objeto objeto : this.InventarioModelo.getObjetos()) {
            nombresObjetos.add(objeto.getNombre());
        }
        return nombresObjetos;
    }

    /**
     *  Actualiza el texto donde está el carro.
     */
    private void actualizarCarro() {
        this.Vista.getListaCarro().setText(this.Modelo.toString());
    }

    /**
     * Dado el controlador "Padre", se actualiza la empresa de la cual se
     * extraen los modelos para este mismo controlador.
     */
    private void actualizarEmpresa() {
        int aumentoFondos = this.Modelo.getTotal();
        this.Padre.actualizarModelo(this.Padre.obtenerFondosModelo() + aumentoFondos);
    }

    /**
     * Actualiza el inventario modelo, reestableciendo la cantidad en base a
     * la cantidad de objetos en el carro.
     */
    private void actualizarInventario() {
        for (Objeto o : this.InventarioModelo.getObjetos()) {
            o.setCantidad(o.getCantidad() - cantidadObjetoEnCarro(o));
        }
    }

    /**
     * Cuenta la cantidad de un objeto "O" dentro del carro.
     * @param o
     * @return
     */
    private int cantidadObjetoEnCarro(Objeto o) {
        int cantidad = 0;
        for (Objeto p : this.Modelo.getCarro()) {
            if (p.equals(o)) cantidad ++;
        }
        return cantidad;
    }

    /**
     * Getters y setters.
     */
    public Inventario getInventarioModelo() {
        return this.InventarioModelo;
    }

    public void setInventarioModelo(Inventario modelo) {
        this.InventarioModelo = modelo;
    }

    public void setObjetoModelo(Objeto objetoModelo) {
        this.ObjetoModelo = objetoModelo;
    }


}
