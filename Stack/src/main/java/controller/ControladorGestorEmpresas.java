package controller;

import com.google.gson.Gson;
import model.Empresa;
import utillities.GestorArchivos;
import utillities.Parametrizador;
import utillities.Validaciones;
import view.GUIGestorEmpresas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ControladorGestorEmpresas
        extends ControladorFactory
        implements ActionListener {

    private ControladorPrincipal Padre;
    private ControladorGestorInventario GestorInventario;
    private ControladorGestorVentas GestorVentas;
    private GUIGestorEmpresas Vista;
    private Empresa Modelo;

    /**
     * Constructor del controlador, recibe como parámetro su controlador
     * "Padre" (Principal).
     * @param principal
     */
    public ControladorGestorEmpresas(ControladorPrincipal principal) {
        this.Padre = principal;
        this.Vista = new GUIGestorEmpresas(this);
        this.Vista.setActionListener(this);
        inicializarControladores();
    }

    /**
     * Visibiliza la ventana de su respectiva GUI, y establece los campos
     * de texto en los predeterminados.
     */
    public void iniciar() {
        this.Vista.setVisible(true);
        asignarTextoDefault();
    }

    /**
     * Inicializa los controladores "Hijos", o sea, aquellos a los cuales
     * se va a llamar mediante botones (Gestor Empresa y Gestor Inventario).
     */
    private void inicializarControladores() {
        this.GestorInventario = new ControladorGestorInventario(this);
        this.GestorVentas = new ControladorGestorVentas(this);
    }

    /**
     * Escucha los eventos de todos los botones, excepto aquellos de la lista
     * de botones de su correspondiente GUI (Tiene un método aparte).
     * @param a the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent a) {
        try {
            if (a.getSource().equals(this.Vista.getVolver())) {
                volverAPadre();
            } else if (a.getSource().equals(this.Vista.getCrearEmpresa())) {
                edicionNuevaEmpresa();
            } else if (a.getSource().equals(this.Vista.getGuardarEmpresa())) {
                generarEmpresa();
            } else if (a.getSource().equals(this.Vista.getEliminarEmpresa())) {
                eliminarEmpresa();
            } else if (a.getSource().equals(this.Vista.getGestionarInventario())) {
                abrirGestorInventario();
            } else if (a.getSource().equals(this.Vista.getGestionarVentas())) {
                abrirGestorVentas();
            } else {
                listenerBotonesEmpresas(a);
            }
        } catch (Exception e) {
            mostrarError(e, this.Vista.getTextoDefault());
        } finally {
            actualizarListenersBotonesEmpresas();
            asignarTextoDefault();
        }
    }

    /**
     * Escucha los eventos de la lista de botones de la GUI.
     * @param a
     * @throws Exception
     */
    private void listenerBotonesEmpresas(ActionEvent a) throws Exception {
        for (JButton boton : this.Vista.getArregloListaBotones()) {
            if (a.getSource() == boton) {
                editarEmpresa(boton.getText());
                this.Vista.actualizarListaBotones();
            }
        }
    }

    /**
     * Actualiza los listeners de la lista de botones de empresa (Útil cuando
     * se agregan nuevos botones a esta lista).
     */
    private void actualizarListenersBotonesEmpresas() {
        for (JButton boton : this.Vista.getArregloListaBotones()) {
            this.Vista.setActionListener(boton, this);
        }
    }

    /**
     * Establece como empresa modelo una empresa con datos "Neutros",
     * posteriormente, se edita esta misma.
     */
    private void edicionNuevaEmpresa() {
        establecerModelo(new Empresa());
        rellenarTextFieldsDatosModelo();
        this.Vista.visibilizarPanelEdicion();
    }

    /**
     * Para volver a su ventana "Padre", la principal.
     */
    private void volverAPadre() {
        this.Vista.setVisible(false);
        this.Vista.dispose();
        this.Padre.iniciar();
    }

    /**
     * Cierra esta ventana, y pasa a la del gestor de inventario.
     */
    private void abrirGestorInventario() {
        this.Vista.dispose();
        this.GestorInventario.setModelo(this.Modelo.getInventario());
        this.GestorInventario.iniciar();
        guardarInventarioGestorInventario();
    }

    /**
     * Llama al gestor de inventario, y establece el inventario del modelo
     * como el resultante de la gestion inventario al guardar datos.
     */
    private void guardarInventarioGestorInventario() {
        this.Modelo.setInventario(this.GestorInventario.getModelo());
    }


    /**
     * Cierra esta ventana, y pasa a la de gestor de ventas.
     */
    private void abrirGestorVentas() {
        this.Vista.dispose();
        this.GestorVentas.setInventarioModelo(this.Modelo.getInventario());
        this.GestorVentas.iniciar();
        guardarInventarioGestorVentas();
    }

    /**
     * Llama al gestor de ventas, y establece el inventario del modelo
     * como el resultante de la gestion de la venta al guardar datos.
     */
    private void guardarInventarioGestorVentas() {
        this.Modelo.setInventario(this.GestorVentas.getInventarioModelo());
    }

    /**
     * Desde los campos de texto correspondientes, se extraen los datos, se
     * transforman a sus respectivos tipos de valores, para despues actualizar
     * el modelo, y guardar un archivo del mismo. Finalizado todo, se vuelve
     * al panel default.
     */
    private void generarEmpresa() {
        try {
            String fondosString = this.Vista.getCampoFondos().getText();
            String nombre = this.Vista.getCampoNombreEmpresa().getText();

            controlarCampos(nombre, fondosString);

            int fondos = Validaciones.stringAInt(fondosString);

            actualizarModelo(nombre, fondos);
            gestionarSobreEscritura();

            actualizarParametros();
            volverADefault();
        } catch (Exception e) {
            mostrarError(e, this.Vista.getTextoEdicion());
        }
    }

    /**
     * De la GUI, actualiza la lista de botones, y establece el texto
     * de edición de empresas en su valor predeterminado.
     */
    private void actualizarParametros() {
        this.Vista.actualizarListaBotones();
        this.Vista.reiniciarTextoEdicion();
    }

    /**
     * LLama a los respectivos métodos para controlar los campos, tirando una
     * excepción por si algo sale mal.
     * @param nombre
     * @param fondosString
     * @throws Exception
     */
    private void controlarCampos(String nombre,
                                 String fondosString) throws Exception {
        String error = "";
        error = controlarCampoNombre(nombre, error);
        error = controlarCampoFondos(fondosString, error);
        System.out.println(error);
        if (error.length() != 0) throw new Exception(error);
    }

    /**
     * Verifica que el nombre cumpla con los estándares para un nombre de
     * archivo para cualquier sistema operativo. Si no se cumple, el mensaje
     * de error cambia.
     * @param nombre
     * @param error
     * @return
     */
    private String controlarCampoNombre(String nombre,
                                      String error) {
        try {
            Validaciones.validarNombreArchivo(nombre);
            return error;
        } catch (Exception e) {
            String nombreModelo = this.Modelo.getNombre();
            this.Vista.getCampoNombreEmpresa().setText(nombreModelo);
            return error + e.getMessage() + "\n";
        }
    }

    /**
     * Verifica que el campo de fondos cumpla con los requerimientos, es decir,
     * que sea positivo y que no sea mayor al Integer.MAX_INT. Si no cumple
     * cualquiera de las dos, el mensaje de error cambia.
     * @param fondosString
     * @param error
     * @return
     */
    private String controlarCampoFondos(String fondosString,
                                     String error) {
        try {
            int fondos = Validaciones.stringAInt(fondosString);
            Validaciones.validarPositivo(fondos);
            return error;
        } catch (Exception e) {
            String fondosModelo = String.valueOf(this.Modelo.getFondos());
            this.Vista.getCampoFondos().setText(fondosModelo);
            return error + e.getMessage() + "\n";
        }
    }

    /**
     * Toma la empresa modelo, y la guarda en un archivo ".json".
     * @throws Exception
     */
    protected void guardarEmpresa() throws Exception {
        String jsonEmpresa = Parametrizador.aJson(this.Modelo);
        String rutaGuardado = "Empresas/" +
                this.Modelo.getNombre() + ".json";
        GestorArchivos.crearArchivo(rutaGuardado, jsonEmpresa);
    }

    /**
     * Maneja los casos de posible sobreescritura, consultandole al usuario si,
     * efectivamente, desea sobreescribir el archivo de la respectiva empresa.
     * @throws Exception
     */
    protected void gestionarSobreEscritura() throws Exception {
        if (obtenerEmpresas().contains(this.Modelo.getNombre())) {
            if(!confirmarAccion("Se sobreescribirá la empresa")) {
                return;
            }
        }
        guardarEmpresa();
    }

    /**
     * Dado un error, y un TextPane, muestra el error en el último.
     * @param e
     * @param j
     */
    private void mostrarError(Exception e, JTextPane j) {
        j.setText(e.getMessage());
    }

    /**
     * Dado un arreglo de empresa, retorna una lista con el nombre de
     * cada una de estas.
     * @return
     */
    public ArrayList<String> obtenerNombresEmpresas(){
        try {
            ArrayList<String> nombresEmpresas = new ArrayList<>();
            for (Empresa e : obtenerEmpresas()) {
                nombresEmpresas.add(e.getNombre());
            }
            return nombresEmpresas;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Obtiene un arreglo con todas las empresas en el directorio "Empresas/".
     * @return
     * @throws Exception
     */
    private ArrayList<Empresa> obtenerEmpresas() throws Exception {
        ArrayList<Empresa> empresas = new ArrayList<>();

        for (String archivo : GestorArchivos.listaArchivos("Empresas")) {
            if (Validaciones.esJson("Empresas/" + archivo)) {
                agregarListaEmpresa(empresas, "Empresas/" + archivo);
            }
        }

        if (empresas.size() == 0) {
            throw new Exception("No hay empresas en el directorio");
        }

        return empresas;
    }

    /**
     * Agrega un objeto de tipo empresa a la lista de empresas (Del método
     * obtenerEmpresas).
     * @param listaEmpresas
     * @param ruta
     */
    private void agregarListaEmpresa(ArrayList<Empresa> listaEmpresas,
                                     String ruta) {
        try {
            Empresa e = importarEmpresa(ruta);
            listaEmpresas.add(e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Dada una ruta, importa una empresa asociada al archivo potencial de
     * empresa. Si no corresponde al tipo, arroja una excepción.
     * @param ruta
     * @return
     * @throws Exception
     */
    private Empresa importarEmpresa(String ruta) throws Exception {
        try {
            Gson gson = new Gson();
            String jsonEmpresa = GestorArchivos.leerArchivo(ruta);
            return gson.fromJson(jsonEmpresa, Empresa.class);
        } catch (Exception e) {
            String msj = "El archivo no corresponde al objeto correspondiente";
            throw new Exception(msj);
        }
    }

    /**
     * Dado un nombre, y unos fondos en especpífico, establece los valores
     * correspondientes del modelo en los ingresados.
     * @param nombre
     * @param fondos
     */
    private void actualizarModelo(String nombre, int fondos) {
        this.Modelo.setNombre(nombre);
        this.Modelo.setFondos(fondos);
        rellenarTextFieldsDatosModelo();
    }

    /**
     * Dado fondos, establece el valor de fondos del modelo en el ingresado.
     * @param fondos
     */
    public void actualizarModelo(int fondos) {
        this.Modelo.setFondos(fondos);
        rellenarTextFieldsDatosModelo();
    }

    /**
     * Obtiende los fondos de la empresa modelo.
     * @return
     */
    public int obtenerFondosModelo() {
        return this.Modelo.getFondos();
    }

    /**
     * Para todo TextField en el apartado de edición de empresas, se rellena
     * cada uno con sus datos correspondientes.
     */
    private void rellenarTextFieldsDatosModelo() {
        String nombreModelo = this.Modelo.getNombre();
        this.Vista.getCampoNombreEmpresa().setText(nombreModelo);

        String fondosModelo = Integer.toString(this.Modelo.getFondos());
        this.Vista.getCampoFondos().setText(fondosModelo);
    }

    /**
     * Establece la empresa modelo en la empresa ingresada como parámetro.
     * @param modelo
     */
    private void establecerModelo(Empresa modelo) {
        this.Modelo = modelo;
    }

    /**
     * Dada un nombre de empresa, se importa la empresa y se pasa al apartado
     * de edición, si la empresa no existe, se arroja una excepción.
     * @param nombreEmpresa
     * @throws Exception
     */
    private void editarEmpresa(String nombreEmpresa) throws Exception {
        try {
            Empresa modelo = importarEmpresa("Empresas/"
                    + nombreEmpresa + ".json");
            this.Modelo = modelo;
            rellenarTextFieldsDatosModelo();
            this.Vista.visibilizarPanelEdicion();
        } catch (Exception e) {
            String msj = "El archivo no corresponde al objeto correspondiente";
            throw new Exception(msj);
        }
    }

    /**
     * Se pide la confirmación de la acción, posteriormente, se elimina la
     * empresa del directorio de empresas, y se establece el objeto modelo como
     * un "Null".
     */
    private void eliminarEmpresa() {
        if (confirmarAccion("La acción será irreversible")) {
            String rutaEmpresa = "Empresas/" + this.Modelo.getNombre() + ".json";
            GestorArchivos.eliminarArchivo(rutaEmpresa);
            this.Modelo = null;
            this.Vista.actualizarListaBotones();
            volverADefault();
        } else {
            this.Vista.getTextoEdicion().setText("No se eliminará la empresa");
        }
    }

    /**
     * Vuelve al panel default.
     */
    private void volverADefault() {
        this.Vista.visibilizarPanelDefault();
        establecerModelo(null);
    }

    /**
     * Si hay empresas, el texto default del panel default indica que se
     * seleccione o se cree una empresa, de lo contrario, dice que no hay
     * empresas.
     */
    private void asignarTextoDefault() {
        if (this.Vista.getArregloListaBotones().size() == 0) {
            this.Vista.getTextoDefault().setText("No hay empresas");
        } else  {
            this.Vista.getTextoDefault().setText("Selecciona una empresa o...");
        }
    }

}
