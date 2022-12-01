package utillities;

import java.io.IOException;

public class Validaciones {

    public static int stringAInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("No se ha ingresado un número");
        }
    }

    public static void validarPositivo(int numero) {
        overflowInt(numero);
        if (numero <= 0) {
            throw new IllegalArgumentException("El número no es positivo");
        }
    }

    public static void validarNombreArchivo(String nombre) throws Exception {
        stringAlfaNumerica(nombre);
    }

    public static void stringAlfaNumerica(String string) throws Exception {
        String caracteresValidos = "^[A-za-z0-9._ d]{1,255}$";
        if (string == null || !string.matches(caracteresValidos)) {
            throw new IOException("La string contiene caracteres ilegales");
        }
    }

    public static void overflowInt(int numero) {
        if (Math.abs(numero) >= (Integer.MAX_VALUE-1)) {
            throw new RuntimeException("\nEl valor absoluto del número es mayor al máximo procesable\n");
        }
    }

    public static void validarString(String string) throws Exception {
        String aux = string.replaceAll(" ", "");
        if (aux.length() == 0) {
            throw new Exception("Texto está vacío");
        }
        if (string.length() >= 256) {
            throw new Exception("Texto debe contener 256 o menos caracteres");
        }
    }

    public static String obtenerExtensionObjeto(String ruta) {
        String extension = "";
        int i = ruta.lastIndexOf('.');
        if (i >= 0) {
            extension = ruta.substring(i + 1);
        }
        return extension;
    }

    public static boolean esJson(String ruta) {
        return obtenerExtensionObjeto(ruta).equals("json");
    }
}
