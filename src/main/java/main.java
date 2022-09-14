import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        mostrarOpcionesMenu();
        //escogerOpcionMenu();
    }

    // Opciones del menú principal
    public static void mostrarOpcionesMenu() {
        System.out.println("""
                [STOCK, PROTOTIPO]
                ¿Qué deseas realizar?
                [1] Crear Inventario
                [2] Modificar Inventario
                [3] Mostrar lista de inventarios
                [4] Mostrar Inventario
                [5] Modificar cantidad de objeto
                """);
    }

    /*public static void escogerOpcionMenu() {
        switch (validarInt()) {
            case 1 -> crearInventario();
            case 2 -> modificarInventario();
            case 3 -> listaInventarios();
            case 4 -> mostrarInventario();
            case 5 -> modificarCantidad();
            default -> System.out.println("No se reconoce la opción ingresada");
        }
        menu();
    }*/

    public static void modificarCantidad(HashMap<String, Integer> Inventario) {

    }

    public static void mostrarInventario(HashMap<String, Integer> inventario) {
        System.out.println("Objeto - Cantidad :");
        for (Map.Entry<String, Integer> objeto : inventario.entrySet()) {
            System.out.println(objeto.getKey() + "-" + objeto.getValue());
        }
    }

    public static void listaInventarios() {
        File f = new File("Inventarios");
        String[] archivos = f.list();
        int indiceArchivos = 0;
        System.out.println("Mostrando archivos:");
        for (String archivo : archivos) {
            System.out.println("[" + indiceArchivos + "] " + archivo);
        }
    }


    public static void crearInventario() {
        HashMap <String, Integer> inventario = new HashMap<>();
        guardarInventario(inventario);
    }

    public static void modificarInventario() {
        HashMap<String, Integer> inventario = cargarInventario(seleccionarArchivoInventario());


    }

    public static void guardarInventario(HashMap<String, Integer> inventario) {
        String inventarioAString = inventarioAString(inventario);
        System.out.println("¿Con qué nombre deseas guardar el archivo?");
        String nombreArchivo = formatoGuardado(validarString());
        escribirArchivo(nombreArchivo, inventarioAString);
    }

    public static String inventarioAString(HashMap<String, Integer> inventario) {
        String retorno = "";
        int contador = 1;
        for (Map.Entry<String, Integer> objeto : inventario.entrySet()) {
            retorno += objeto.getKey()+":"+objeto.getValue();
            if (contador<inventario.size()) {
                retorno+="\n";
                contador++;
            }
        }
        return retorno;
    }

    public static String seleccionarArchivoInventario() {
        String[] inventarios = mostrarArchivos();
        int opcion = validarInt();
        if (opcion > 0 & opcion < inventarios.length) {
           return abrirArchivoInventario("Inventarios/"+inventarios[opcion]);
        } else {
            System.out.println("Seleccione otro índice de inventario");
            return seleccionarArchivoInventario();
        }
    }

    public static String abrirArchivoInventario(String ruta) {
        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo del inventario no puede ser leido");
        }
        return texto;
    }

    public static HashMap<String, Integer> cargarInventario (String ruta) {
        HashMap<String, Integer> mapaCargado = new HashMap<>();
        String inventarioString = abrirArchivoInventario(ruta);
        String[] mapaBruto = inventarioString.split("\n");

        for (String objetoBruto : mapaBruto) {
            String[] objeto = objetoBruto.split(":");
            mapaCargado.put(objeto[0],Integer.parseInt(objeto[1]));
        }

        return mapaCargado;
    }

    public static String[] mostrarArchivos() {
            File f = new File("Inventarios");
            String[] archivos = f.list();
            int indiceArchivos = 0;
            System.out.println("Mostrando archivos:");
            for (String archivo : archivos) {
                System.out.println("[" + indiceArchivos + "] " + archivo);
            }
            return archivos;
    }

    public static void escribirArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
            System.out.println("El inventario se ha guardado exitosamente en " + ruta);
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser creado.");
        }
    }

    public static String formatoGuardado(String nombreArchivo) {
        try {
            return nombreArchivo.equals("") ? "Inventarios/["+nombreArchivo+"].txt" : formatoGuardado(validarString());
        } catch (NullPointerException e) {
            System.out.println("El nombre del archivo es nulo, inserte uno nuevo");
            return formatoGuardado(validarString());
        }
    }

    public static String validarString() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextLine();
        }
        catch (InputMismatchException e) {
            System.out.println("No has ingresado una string");
            input.nextLine();
            return validarString();
        }
    }

    public static int validarInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("No has ingresado un valor numérico entero");
            input.nextLine();
            return validarInt();
        }
    }











}
