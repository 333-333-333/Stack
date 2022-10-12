package Programas;

import Clases.Empresa;
import Utilidades.GestorArchivos;
import Utilidades.GestorErrores;
import Utilidades.Parametrizador;
import Utilidades.Validaciones;
import com.google.gson.Gson;

public class GestorEmpresas {

// Inicia el gestor de empresas.
    protected static void iniciar() {
        inicializarCarpeta();
        menuGestorEmpresas();
    }

// Inicializa el directorio Empresas.
    private static void inicializarCarpeta() {
        String ruta = "Empresas";
        if (!GestorArchivos.verificarExistencia(ruta)) {
           GestorArchivos.crearDirectorio(ruta);
        }
    }

// Menú de la UI del gestor de empresas.
    private static void menuGestorEmpresas() {
        mostrarOpciones();
        opcionMenu(Validaciones.validarInt(1, 3));
    }

    private static void mostrarOpciones() {
        System.out.println("\n" + """
            -
            [Menu gestión de empresas]
            
            ¿Qué deseas hacer?
                [1] Gestionar empresa
                [2] Agregar nueva empresa
                [3] Salir
            -
                """);
    }

    private static void opcionMenu(int opcion) {
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
            seleccionada.menuGestionInventario();
            guardarEmpresa(seleccionada);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Agregar nueva empresa.
    private static void agregarNuevaEmpresa() {
        Empresa nuevaEmpresa = crearEmpresa();
        guardarEmpresa(nuevaEmpresa);
    }

// Utilidades
    /* En base a un archivo ".json" asociado a una empresa, retorna un objeto
    de la clase empresa.*/
    private static Empresa abrirEmpresa() throws Exception {
        Gson gson = new Gson();
        String jsonEmpresa = seleccionarJsonEmpresa();
        return gson.fromJson(jsonEmpresa, Empresa.class);
    }

    //Retorna un objeto de la clase Empresa.
    private static Empresa crearEmpresa() {
        System.out.println("¿Qué nombre deseas que tenga la empresa");
        return new Empresa(Validaciones.validarString());
    }

    // Guarda un objeto de la clase Empresa en un archivo ".json".
    private static void guardarEmpresa(Empresa aGuardar) {
        String jsonEmpresa = Parametrizador.aJson(aGuardar);
        String rutaGuardado = "Empresas/" +
                Parametrizador.obtenerNombreJson();
        GestorArchivos.crearArchivo(rutaGuardado, jsonEmpresa);
    }

    // Selecciona el archivo una empresa dentro de la carpeta de empresas.
    private static String seleccionarJsonEmpresa() throws Exception {
        String[] listaEmpresas = GestorArchivos.listaArchivos("Empresas");
        GestorErrores.erroresArreglos(listaEmpresas);
        GestorArchivos.mostrarListaArchivos("Empresas");

        System.out.println("Selecciona el índice de la empresa");
        int indice = Validaciones.validarInt(0, listaEmpresas.length-1);
        String archivo = listaEmpresas[indice];

        return GestorArchivos.leerArchivo("Empresas/" + archivo);
    }




}
