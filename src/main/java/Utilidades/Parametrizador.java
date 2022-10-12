package Utilidades;

import com.google.gson.Gson;

public class Parametrizador {

    public static String obtenerNombreJson() {
        System.out.println("¿Con qué nombre deseas guardar el archivo?"
                         + "(Obvie la terminación del tipo de archivo.)");
        String nombreArchivo = Validaciones.validarNombreArchivo();
        return nombreArchivo + ".json";
    }

    public static String aJson(Object objeto) {
        Gson g = new Gson();
        return g.toJson(objeto);
    }

}
