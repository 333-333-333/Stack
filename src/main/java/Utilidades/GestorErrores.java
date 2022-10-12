package Utilidades;

import Clases.Objeto;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GestorErrores {


// Gestor de errores para arreglos convencionales.
    public static void erroresArreglos(Object[] arreglo) {
        arregloVacio(arreglo);
        arregloNulo(arreglo);
    }

    private static void arregloVacio(Object[] arreglo) {
        String clase = arreglo.getClass().getSimpleName().toString();
        if (arreglo.length==0) {
            throw new ArrayIndexOutOfBoundsException("[Error] "
            +"\n" + clase + " vacío.");
        }
    }

    private static void arregloNulo(Object[] arreglo) {
        String clase = arreglo.getClass().getSimpleName().toString();
        if (arreglo == null) {
            throw new NullPointerException("[Error] "
            +"\n" + clase + " nulo.");
        }
    }

// Gestor de errores para nombres de archivos en Windows/Linux.
    public static void nombreArchivoIlegal(String nombre) throws Exception {
        String caracteresValidos = "^[A-za-z0-9.]{1,255}$";
        if (nombre == null
        || !nombre.matches(caracteresValidos)
        || nombre.length() > 32000) {
            throw new FileNotFoundException("[Error]"
            + "\nEl nombre del archivo contiene caracteres ilegales.");
        }
    }

// Gestor de errores para overflow de números enteros
    public static void overflowInt(int numero) {
        if (Math.abs(numero) >= (Integer.MAX_VALUE-1)) {
            throw new RuntimeException("[Error]"
            + "\nEl valor absoluto del número es mayor al máximo procesable");
        }
    }


}
