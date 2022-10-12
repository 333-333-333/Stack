package Utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validaciones {

// Validación de números
    // Valida en los enteros reales
    public static int validarInt() {
        Scanner input = new Scanner(System.in);
        input.nextLine();
        try {
            int real = input.nextInt();
            GestorErrores.overflowInt(real);
            return real;
        } catch (InputMismatchException e) {
            System.err.println("No se ha ingresado un número,");
            return validarInt();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return validarInt();
        }
    }

    // Valida en los enteros reales para un rango [cotaInferior, cotaSuperior]
    public static int validarInt(int cotaInferior, int cotaSuperior) {
        Scanner input = new Scanner(System.in);
        try {
            int real = input.nextInt();
            GestorErrores.overflowInt(real);
            if (!(real >= cotaInferior && real <= cotaSuperior)) {
                System.err.println("El número no está entre "
                + cotaInferior + " y " + cotaSuperior +".");
                return validarInt(cotaInferior, cotaSuperior);
            }
            return real;
        } catch (InputMismatchException e) {
            System.err.println("No se ha ingresado un número.");
            input.nextLine();
            return validarInt(cotaInferior, cotaSuperior);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            input.nextLine();
            return validarInt(cotaInferior, cotaSuperior);
        }
    }

    // Valida en los enteros positivos
    public static int validarPositivo() {
        Scanner input = new Scanner(System.in);
        try {
            int positivo = input.nextInt();
            GestorErrores.overflowInt(positivo);
            if (positivo>=0) return positivo;
        } catch (InputMismatchException e) {
            System.err.println("No se ha ingresado un número.");
            input.nextLine();
            return validarPositivo();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            input.nextLine();
            return validarPositivo();
        }
        System.err.println("El número no es positivo");
        return validarPositivo();
    }

    // Valida en los cardinales (0 + enteros positivos)
    public static int validarCardinal() {
        Scanner input = new Scanner(System.in);
        input.nextLine();
        try {
            int cardinal = input.nextInt();
            GestorErrores.overflowInt(cardinal);
            return (cardinal >= 0) ? cardinal : validarPositivo();
        } catch (InputMismatchException e) {
            System.err.println("No se ha ingresado un número.");
            return validarCardinal();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return validarCardinal();
        }
    }

// Validación de strings
    // Valida que es una string
    public static String validarString() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("No se ha ingresado texto.");
            return validarString();
        }
    }

    /* Valida que una string no contenga caracteres ilegales para el manejo de
    archivos en Windows y en distribuciones de Linux.*/
    public static String validarNombreArchivo() {
        try {
            String archivo = validarString();
            GestorErrores.nombreArchivoIlegal(archivo);
            return archivo;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return validarNombreArchivo();
        }
    }

}
