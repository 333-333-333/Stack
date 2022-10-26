package Programas;

import Objetos.Objeto;
import Utilidades.Validaciones;

public class GestorObjetos {

// Menú del gestor de objetos.
    public static void menu(Objeto aModificar) {
        mostrarOpciones();
        opcionMenu(aModificar);
    }

    private static void mostrarOpciones() {
        System.out.println("[Gestor de objetos]\n"
                           + "\n"
                           + "¿Qué deseas hacer?\n"
                           + "\n"
                           + "[1] Establecer descripción.\n"
                           + "[2] Establecer nombre.\n"
                           + "[3] Establecer proveedor.\n"
                           + "[4] Establecer cantidad.\n"
                           + "[5] Establecer cantidad crítica.\n"
                           + "[6] Establecer precio.\n"
                           + "[7] Salir.\n");
    }

    private static void opcionMenu(Objeto aModificar) {
        boolean quedarse = true;
        switch (Validaciones.validarInt(1,7)){
            case 1 -> establecerDescripcion(aModificar);
            case 2 -> establecerNombre(aModificar);
            case 3 -> establecerProveedor(aModificar);
            case 4 -> menuModificarCantidad(aModificar);
            case 5 -> establecerCantidadCritica(aModificar);
            case 6 -> establecerPrecio(aModificar);
            case 7 -> quedarse = false;
        } if (quedarse) {
            menu(aModificar);
        } else {
            System.out.println("\nSaliendo del gestor de objetos.\n");
        }
    }

    // Método para crear un objeto.
    public static Objeto crearObjeto() {
        String nombre = crearNombre();
        String descripcion = crearDescripcion();
        String proveedor = crearProveedor();

        int cantidadCritica = crearCantidadCritica();
        int precio = crearPrecio();

        return new Objeto(nombre,
                proveedor,
                descripcion,
                cantidadCritica,
                precio);
    }

    // Sub métodos para parametrizar los valores que crearán al objeto.
    private static String crearDescripcion() {
        System.out.println("\nInserte una descripción.\n");
        return Validaciones.validarString();
    }

    private static String crearNombre() {
        System.out.println("\n¿Qué nombre tiene el objeto?\n");
        return Validaciones.validarString();
    }

    private static String crearProveedor() {
        System.out.println("\nInserte el proveedor del objeto.\n");
        return Validaciones.validarString();
    }

    private static int crearCantidadCritica() {
        System.out.println("\nInserte la cantidad crítica del objeto.\n");
        return Validaciones.validarPositivo();
    }

    private static int crearPrecio() {
        System.out.println("\nInserte el precio del objeto.\n");
        return Validaciones.validarCardinal();
    }

// Métodos para modificar valores.
    // Para establecer la descripcion.
    public static void establecerDescripcion(Objeto objeto) {
        System.out.println("\nEstableciendo descripcion.\n");
        String desc = crearDescripcion();

        objeto.setDesc(desc);
    }

    // Para establecer el nombre del objeto.
    public static void establecerNombre(Objeto objeto) {
        System.out.println("\nEstableciendo nombre.\n");
        String nombre = crearNombre();

        objeto.setNombre(nombre);
    }

    // Para establecer proveedor.
    public static void establecerProveedor(Objeto objeto) {
        System.out.println("\nEstableciendo proveedor.\n");
        String proveedor = crearProveedor();

        objeto.setProveedor(proveedor);
    }

// Métodos para modificar cantidades.
    // Menú.
    private static void menuModificarCantidad(Objeto aModificar) {
        mostrarOpcionesMenuModificarCantidad();
        opcionMenuModificarCantidad(aModificar);
    }

    private static void mostrarOpcionesMenuModificarCantidad() {
        System.out.println("[Modificar cantidad]\n"
                           + "\n"
                           + "¿Qué deseas hacerle a la cantidad?\n"
                           + "\n"
                           + "[1] Aumentarla.\n"
                           + "[2] Decrementarla.\n"
                           + "[3] Establecerla.\n"
                           + "[4] Salir\n");
    }

    private static void opcionMenuModificarCantidad(Objeto aModificar) {
        switch (Validaciones.validarInt(1,4)) {
            case 1 -> aumentarCantidad(aModificar);
            case 2 -> decrementarCantidad(aModificar);
            case 3 -> establecerCantidad(aModificar);
            case 4 -> System.out.println("\nSaliendo del apartado.\n");
            default -> {
                System.err.println("[Error]"
                    + "\nOpción inválida.\n");
                menuModificarCantidad(aModificar);
            }
        }

    }

    // Para aumentar la cantidad.
    private static void aumentarCantidad(Objeto objeto) {
        int cantidadActual = objeto.getCantidad();

        System.out.println("\n¿En qué cantidad deseas aumentarlo?\n");
        int aumento = Validaciones.validarPositivo();

        objeto.setCantidad(cantidadActual + aumento);

        advertir(objeto);
    }

    public static void aumentarCantidad(Objeto objeto, int aumento) {
        if (aumento<=0) {
            throw new IllegalArgumentException("El aumento es negativo o 0.");
        }
        int cantidadActual = objeto.getCantidad();

        objeto.setCantidad(cantidadActual + aumento);
    }

    // Para decrementarla.
    private static void decrementarCantidad(Objeto objeto) {
        int cantidadActual = objeto.getCantidad();

        System.out.println("\n¿En qué cantidad deseas decrementarlo?"
            + "\n(Entre 1 y " + (cantidadActual - 1) + ")\n");
        int decremento = Validaciones.validarInt(1, cantidadActual - 1);

        objeto.setCantidad(cantidadActual - decremento);

        advertir(objeto);
    }

    public static void decrementarCantidad(Objeto objeto, int decremento) {
        int cantidadActual = objeto.getCantidad();

        if (decremento<=0) {
            throw new IllegalArgumentException("El decremento es "
                    + "negativo o 0");
        }
        if (decremento > cantidadActual) {
            throw new IllegalArgumentException("El decremento es " +
                    "mayor que la cantidad actual.");
        }

        objeto.setCantidad(cantidadActual-decremento);
    }

    // Para estasblecer la cantidad.
    private static void establecerCantidad(Objeto objeto) {
        System.out.println("\n¿En qué cantidad deseas establecer el objeto?\n");
        int cantidadNueva = Validaciones.validarPositivo();

        objeto.setCantidad(cantidadNueva);

        advertir(objeto);
    }

    // Para establecer la cantidad crítica.
    private static void establecerCantidadCritica(Objeto objeto) {
        System.out.println("\nEstableciendo cantidad crítica.\n");
        int cantidadCritica = crearCantidadCritica();

        objeto.setCantidadCritica(cantidadCritica);
        advertir(objeto);
    }

    // Para establecer el precio.
    private static void establecerPrecio(Objeto objeto) {
        System.out.println("\nEstableciendo precio.\n");
        int precio = crearPrecio();

        objeto.setPrecio(precio);
    }

    // Advierte en caso que la cantidad sea menor o igual a la crítica.
    private static void advertir(Objeto objeto) {
        int cantidadCritica = objeto.getCantidadCritica();
        int cantidad = objeto.getCantidad();

        if (cantidad<= cantidadCritica) {
            System.err.println("\nTen cuidado, la cantidad es crítica."
                    + "\n(Menor o igual a " + cantidadCritica + ")\n");
        }
    }

}
