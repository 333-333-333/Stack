package Utilidades;

import java.io.FileNotFoundException;

public class GestorErrores {

// Gestor de errores para arreglos convencionales.
    public static void erroresArreglos(Object[] arreglo) {
        arregloVacio(arreglo);
        arregloNulo(arreglo);
    }

    // Para cuando un arreglo no tenga objetos.
    private static void arregloVacio(Object[] arreglo) {
        String clase = arreglo.getClass().getSimpleName();
        if (arreglo.length==0) {
            throw new ArrayIndexOutOfBoundsException("\n" + clase + " vacío.\n");
        }
    }

    // Para cuando un arreglo sea un null.
    private static void arregloNulo(Object[] arreglo) {
        String clase = arreglo.getClass().getSimpleName();
        if (arreglo == null) {
            throw new NullPointerException("[Error] "
            +"\n" + clase + " nulo.\n");
        }
    }

// Gestor de errores para nombres de archivos en Windows/Linux.
    public static void nombreArchivoIlegal(String nombre) throws Exception {
        String caracteresValidos = "^[A-za-z\\d.]{1,255}$" + "-/:";
        if (nombre == null
        || !nombre.matches(caracteresValidos)
        || nombre.length() > 32000) {
            throw new FileNotFoundException("[Error]"
            + "\nEl nombre del archivo no es válido.\n");
        }
    }

// Gestor de errores para overflow de números enteros
    public static void overflowInt(int numero) {
        if (Math.abs(numero) >= (Integer.MAX_VALUE-1)) {
            throw new RuntimeException("[Error]"
            + "\nEl valor absoluto del número es mayor al máximo procesable\n");
        }
    }

}
