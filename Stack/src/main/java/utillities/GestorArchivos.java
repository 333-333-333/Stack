package utillities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorArchivos {

    // Para crear un archivo con un contenido de texto.
    public static void crearArchivo(String ruta, String contenido)
            throws Exception {
        try {
            Path archivo = Paths.get(ruta);
            Files.write(archivo, contenido.getBytes());

        } catch (IOException e) {
            throw new IOException("Directorio no encontrado");
        }
    }

    // Para crear un directorio.
    public static void crearDirectorio(String ruta) {
        Path directorio = Paths.get(ruta);
        if (Files.exists(directorio)) {
            System.out.println("El directorio ya existe");
        } else {
            try {
                Validaciones.stringAlfaNumerica(ruta);
                Files.createDirectories(directorio);
                System.out.println("\nEl directorio fue creado exitosamente");
            } catch (Exception e) {
                System.err.println("El directorio no pudo ser creado");
            }
        }
    }

    // Retorna el texto del archivo.
    public static String leerArchivo(String ruta){
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
    public static String[] listaArchivos(String ruta){
        try {
            File directorio = new File(ruta);
            return directorio.list();
        } catch (Exception e) {
            System.err.println("Hay problemas con el directorio");
            return new String[0];
        }
    }

    public static void eliminarArchivo(String ruta) {
        File archivo = new File(ruta);
        try {
            archivo.delete();
        } catch (Exception e) {
            System.err.println("El archivo no existe");
        }
    }

    // Para ver si un directorio existe.
    public static boolean verificarExistenciaDirectorio(String ruta)
            throws Exception {
        Validaciones.stringAlfaNumerica(ruta);
        Path directorio = Paths.get(ruta);
        return Files.exists(directorio);
    }

}