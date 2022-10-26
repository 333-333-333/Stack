package Programas;

import Objetos.Inventario;
import Objetos.Objeto;
import Utilidades.Validaciones;

public class GestorInventario {

    public static void menu(Inventario inventario) {
        try {
            mostrarOpciones();
            opcionMenu(inventario);
        } catch (Exception e) {
            System.err.println("[Error]"
                + e.getMessage());
            menu(inventario);
        }
    }

    private static void mostrarOpciones() {
        System.out.println("[Gestor de inventarios]\n"
                           + "\n"
                           + "¿Qué deseas hacer?\n"
                           + "[1] Agregar objeto.\n"
                           + "[2] Eliminar objeto.\n"
                           + "[3] Modificar existencia de objeto.\n"
                           + "[4] Mostrar inventario.\n"
                           + "[5] Ordenar inventario.\n"
                           + "[5] Salir.\n");
    }

    private static void opcionMenu(Inventario inventario) throws Exception {
        boolean quedarse = true;
        switch (Validaciones.validarInt(1,6)) {
            case 1 -> agregarObjeto(inventario);
            case 2 -> eliminarObjeto(inventario);
            case 3 -> modificarExistencia(inventario);
            case 4 -> mostrarInventario(inventario);
            case 5 -> menuOrdenarInventario(inventario);
            case 6 -> quedarse = false;
        }
        if (quedarse) {
            menu(inventario);
        } else  {
            System.out.println("\nSaliendo del gestor de inventarios.\n");
        }
    }

    private static void agregarObjeto(Inventario inventario) {
        System.out.println("\nAgregando objeto.\n");
        Objeto nuevo = GestorObjetos.crearObjeto();

        inventario.agregarObjeto(nuevo);
    }

    private static void eliminarObjeto(Inventario inventario) throws Exception {
        if (inventario.getObjetos().size()==0) {
            throw new NullPointerException("El inventario está vacío.");
        }

        System.out.println("\nEliminando objeto.\n");

        Objeto eliminado = seleccionarObjeto(inventario);
        inventario.eliminarObjeto(eliminado);
    }

    private static void modificarExistencia(Inventario inventario) {
        Objeto aModificar = seleccionarObjeto(inventario);
        GestorObjetos.menu(aModificar);
    }

    private static void mostrarInventario(Inventario inventario) {
        System.out.println(inventario.toString());
    }

    private static void menuOrdenarInventario(Inventario inventario) {
        mostrarOpcionesOrdenarInventario();
        opcionMenuOrdenarInventario(inventario);
    }

    private static void mostrarOpcionesOrdenarInventario() {
        System.out.println("[Ordenar inventario]\n"
                           + "\n"
                           + "¿Bajo qué parámetros deseas ordenarlo?\n"
                           + "\n"
                           + "[1] De la A a la Z.\n"
                           + "[2] De la Z a la A.\n"
                           + "[3] Cantidad (Creciente).\n"
                           + "[4] Cantidad (Decreciente).\n"
                           + "[5] Precio (Creciente).\n"
                           + "[6] Precio (Decreciente).\n"
                           + "[7] Salir.\n");
    }

    private static void opcionMenuOrdenarInventario(Inventario inventario) {
        switch (Validaciones.validarInt(1, 7)) {
            case 1 -> inventario.ordenarPorNombreAZ();
            case 2 -> inventario.ordenarPorNombreZA();
            case 3 -> inventario.ordenarPorCantidadMenosMas();
            case 4 -> inventario.ordenarPorCantidadMasMenos();
            case 5 -> inventario.ordenarPorPrecioMenosMas();
            case 6 -> inventario.ordenarPorPrecioMasMenos();
            case 7 -> System.out.println("\nSaliendo del ordenador.\n");
            default -> {
                System.out.println("\nOpción inválida.\n");
                menuOrdenarInventario(inventario);
            }
        }
    }

     protected static Objeto seleccionarObjeto(Inventario inventario) {
        int indiceMaximo = inventario.getObjetos().size() - 1;

        mostrarInventario(inventario);
        System.out.println("\nEscóge el índice del objeto deseado:\n");

        return inventario.getObjeto(Validaciones.validarInt(0, indiceMaximo));
    }

}