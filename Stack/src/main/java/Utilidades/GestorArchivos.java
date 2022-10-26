package Utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorArchivos {

    // Para crear un archivo con un contenido de texto.
    public static void crearArchivo(String ruta, String contenido)
            throws Exception {
        GestorErrores.nombreArchivoIlegal(ruta);
        try {
            Path archivo = Paths.get(ruta);
            Files.write(archivo, contenido.getBytes());
            System.out.println("\nSe ha guardado el archivo exitosamente");
        } catch (IOException e) {
            System.err.println("\nEl archivo no pudo ser creado");
        }
    }

    // Para crear un directorio.
    public static void crearDirectorio(String ruta)
            throws Exception {
        GestorErrores.nombreArchivoIlegal(ruta);
        Path directorio = Paths.get(ruta);
        if (Files.exists(directorio)) {
            System.out.println("El directorio ya existe");
        } else {
            try {
                Files.createDirectories(directorio);
                System.out.println("\nEl directorio fue creado exitosamente");
            } catch (IOException e) {
                System.err.println("\nEl directorio no pudo ser creado");
            }
        }
    }

    // Retorna el texto del archivo.
    public static String leerArchivo(String ruta)
            throws Exception {
        GestorErrores.nombreArchivoIlegal(ruta);
        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.err.println("\nEl archivo no pudo ser leido");
        }
        return texto;
    }

    // Para obtener una lista de archivos.
    public static String[] listaArchivos(String ruta) throws Exception {
        GestorErrores.nombreArchivoIlegal(ruta);
        File directorio = new File(ruta);
        return directorio.list();
    }

    // Para mostrar la lista de archivos.
    public static void mostrarListaArchivos(String ruta)
            throws Exception {
        int indiceArchivo = 0;
        for (String archivo : listaArchivos(ruta)) {
            System.out.println("[" + indiceArchivo + "] " + archivo);
            indiceArchivo ++;
        }
    }

    // Para ver si un directorio existe.
    public static boolean verificarExistenciaDirectorio(String ruta)
            throws Exception {
        GestorErrores.nombreArchivoIlegal(ruta);
        Path directorio = Paths.get(ruta);
        return Files.exists(directorio);
    }

}