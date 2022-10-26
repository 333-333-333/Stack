package Programas;

import Objetos.Empresa;
import Objetos.Inventario;
import Utilidades.GestorArchivos;
import Utilidades.GestorErrores;
import Utilidades.Parametrizador;
import Utilidades.Validaciones;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GestorEmpresas {

// Inicia el gestor de empresas.
    protected static void iniciar() throws Exception {
        inicializarCarpeta();
        menuGestorEmpresas();
    }

// Inicializa el directorio Empresas.
    private static void inicializarCarpeta() throws Exception {
        String ruta = "Empresas";
        if (!GestorArchivos.verificarExistenciaDirectorio(ruta)) {
           GestorArchivos.crearDirectorio(ruta);
        }
    }

// Menú de la UI del gestor de empresas.
    private static void menuGestorEmpresas() {
        try {
            mostrarOpcionesGestorEmpresas();
            opcionMenuGestorEmpresas(Validaciones.validarInt(1, 3));
        } catch (Exception e) {
            System.err.println("[Error]\n" + e.getMessage());
            menuGestorEmpresas();
        }
    }

    private static void mostrarOpcionesGestorEmpresas() {
        System.out.println("\n"
                           + "[Menu gestión de empresas]\n"
                           + "\n"
                           + "¿Qué deseas hacer?\n"
                           + "    [1] Gestionar empresa\n"
                           + "    [2] Agregar nueva empresa\n"
                           + "    [3] Salir\n");
    }

    private static void opcionMenuGestorEmpresas(int opcion) throws Exception {
        boolean quedarse = true;
        switch (opcion) {
            case 1 -> gestionarEmpresa();
            case 2 -> agregarNuevaEmpresa();
            case 3 -> quedarse = false;
        }
        if (quedarse) menuGestorEmpresas();
    }

// Funciones del menú
    // Gestionar empresa
    private static void gestionarEmpresa() {
        try {
            Empresa seleccionada = abrirEmpresa();
            Inventario seleccionado = seleccionada.getInventario();
            GestorInventario.menu(seleccionado);
            guardarEmpresa(seleccionada);
        } catch (Exception e) {
            System.err.println("[Error]\n" + e.getMessage());
        }
    }

    // Agregar nueva empresa.
    private static void agregarNuevaEmpresa() throws Exception {
        Empresa nuevaEmpresa = crearEmpresa();
        String[] listaArchivos = GestorArchivos.listaArchivos("Empresas");
        System.out.println(listaArchivos.length);
        if (listaArchivos.length != 0) {
            verificarExistencia(nuevaEmpresa);
        }
        guardarEmpresa(nuevaEmpresa);
    }

    //Retorna un objeto de la clase Empresa.
    private static Empresa crearEmpresa() {
        System.out.println("\n¿Qué nombre deseas que tenga la empresa\n");
        return new Empresa(Validaciones.validarNombreArchivo());
    }

    // En base a un archivo ".json" asociado a una empresa, retorna un objeto
    //de la clase empresa.
    protected static Empresa abrirEmpresa() throws Exception {
        Gson gson = new Gson();
        String jsonEmpresa = seleccionarJsonEmpresa();
        return gson.fromJson(jsonEmpresa, Empresa.class);
    }

    // Selecciona el archivo una empresa dentro de la carpeta de empresas.
    private static String seleccionarJsonEmpresa() throws Exception {
        String[] listaEmpresas = GestorArchivos.listaArchivos("Empresas");
        GestorErrores.erroresArreglos(listaEmpresas);
        GestorArchivos.mostrarListaArchivos("Empresas");

        System.out.println("Selecciona el índice de la empresa.");
        int indice = Validaciones.validarInt(0, listaEmpresas.length-1);
        String archivo = listaEmpresas[indice];

        return GestorArchivos.leerArchivo("Empresas/" + archivo);
    }

// Utilidades
    // Guarda un objeto de la clase Empresa en un archivo ".json".
    private static void guardarEmpresa(Empresa aGuardar) throws Exception {
        String jsonEmpresa = Parametrizador.aJson(aGuardar);
        String rutaGuardado = "Empresas/" +
            aGuardar.getNombre() + ".json";
        GestorArchivos.crearArchivo(rutaGuardado, jsonEmpresa);
    }

    // Verifica si la empresa existe en el directorio de empresas.

    private static void verificarExistencia(Empresa aVerificar) throws Exception {
        String [] listaEmpresas = GestorArchivos.listaArchivos("Empresas");
        List<String> nombresArchivos = Arrays.asList(listaEmpresas);

        if (nombresArchivos.contains(aVerificar.getNombre()+".json")) {
            throw new IOException("Ya hay una empresa con ese nombre.");
        }
    }



}
