package Utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorArchivos {

    public static void crearArchivo(String ruta, String contenido) {
        try {
            Path archivo = Paths.get(ruta);
            Files.write(archivo, contenido.getBytes());
            System.out.println("Se ha guardado el archivo exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser creado");
        }
    }

    public static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
        return texto;
    }

    public static void crearDirectorio(String ruta) {
        Path directorio = Paths.get(ruta);
        if (Files.exists(directorio)) {
            System.out.println("El directorio ya existe");
        } else {
            try {
                Files.createDirectories(directorio);
                System.out.println("El directorio fue creado exitosamente");
            } catch (IOException e) {
                System.out.println("El directorio no pudo ser creado");
            }
        }
    }

    public static boolean verificarExistencia(String ruta) {
        Path directorio = Paths.get(ruta);
        return Files.exists(directorio);
    }

    public static String[] listaArchivos(String ruta) throws Exception {
        GestorErrores.nombreArchivoIlegal(ruta);

        File directorio = new File(ruta);
        String[] listaArchivos = directorio.list();
        GestorErrores.erroresArreglos(listaArchivos);

        return listaArchivos;
    }

    public static void mostrarListaArchivos(String ruta) throws Exception {
        int indiceArchivo = 0;
        for (String archivo : listaArchivos(ruta)) {
            System.out.println("[" + indiceArchivo + "] " + archivo);
            indiceArchivo ++;
        }
    }



}