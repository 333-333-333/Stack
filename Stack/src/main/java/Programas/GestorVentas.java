package Programas;

import Objetos.Boleta;
import Objetos.Empresa;
import Objetos.Inventario;
import Objetos.Objeto;
import Utilidades.GestorArchivos;
import Utilidades.Validaciones;

public class GestorVentas {

    protected static void iniciar() {
        try {
            inicializarCarpeta();
            Empresa seleccionada = seleccionarEmpresa();
            Inventario seleccionado = seleccionada.getInventario();
            menuGestorVentas(seleccionada, seleccionado);
        } catch (Exception e) {
            System.err.println("[Error]\n"
               + e.getMessage());
        }
    }

    private static Empresa seleccionarEmpresa() throws Exception {
        System.out.println("\nBuscando la empresa con la que va a trabajar.\n");
        return GestorEmpresas.abrirEmpresa();
    }

    // Inicializa el directorio Boletas.
    private static void inicializarCarpeta() throws Exception {
        String ruta = "Boletas";
        if (!GestorArchivos.verificarExistenciaDirectorio(ruta)) {
            GestorArchivos.crearDirectorio(ruta);
        }
    }

    private static void menuGestorVentas(Empresa seleccionada,
                                         Inventario seleccionado) {
        try {
            mostrarOpcionesGestorVentas();
            opcionMenuGestorVentas(seleccionada,seleccionado);
        } catch (Exception e) {
            System.err.println("[Error]\n"
               + e.getMessage());
            menuGestorVentas(seleccionada, seleccionado);
        }
    }

    private static void mostrarOpcionesGestorVentas() {
        System.out.println("[Gestor de ventas]\n"
                           + "\n"
                           + "¿Qué deseas hacer?\n"
                           + "\n"
                           + "[1] Crear compra.\n"
                           + "[2] Abrir boleta.\n"
                           + "[3] Salir.\n");
    }

    private static void opcionMenuGestorVentas(Empresa seleccionada,
                                               Inventario productos)
                                                    throws Exception {
        boolean quedarse = true;
        switch (Validaciones.validarInt(1, 3)) {
            case 1 -> inicializarCompra(seleccionada, productos);
            case 2 -> imprimirBoleta();
            case 3 -> quedarse = false;
        }
        if (quedarse) {
            menuGestorVentas(seleccionada, productos);
        } else {
            System.out.println("\nSaliendo del gestor de ventas.\n");
        }
    }

    private static void inicializarCompra(Empresa seleccionada, Inventario productos) {
        Boleta compra = new Boleta();
        menuCompra(seleccionada, compra, productos);
    }

    private static void menuCompra(Empresa seleccionada,
                                   Boleta compra,
                                   Inventario productos) {
        try {
            mostrarOpcionesMenuCompra();
            opcionMenuCompra(seleccionada, compra, productos);
        } catch (Exception e) {
            System.err.println("[Error]\n" + e.getMessage());
            menuCompra(seleccionada, compra, productos);
        }
    }

    private static void mostrarOpcionesMenuCompra() {
        System.out.println("[Menú de compra]\n"
                           + "\n"
                           + "¿Qué deseas hacer?\n"
                           + "\n"
                           + "[1] Agregar objeto al carro.\n"
                           + "[2] Eliminar objeto del carro.\n"
                           + "[3] Terminar compra.\n"
                           + "[4] Cancelar compra.\n");
    }

    private static void opcionMenuCompra(Empresa seleccionada,
                                         Boleta compra,
                                         Inventario productos) throws Exception {
        boolean quedarse = true;
        switch (Validaciones.validarInt(1, 4)) {
            case 1 -> agregarObjeto(compra, productos);
            case 2 -> eliminarObjeto(compra);
            case 3 -> {
                terminarventa(compra, seleccionada);
                quedarse = false;
            }
            case 4 -> quedarse = false;
        }
        if (quedarse) {
            menuCompra(seleccionada, compra, productos);
        } else {
            System.out.println("Saliendo de la compra.");
        }
    }

    // Para agregar un objeto al carro, y disminuir existencia.
    private static void agregarObjeto(Boleta compra, Inventario productos) {
        System.out.println("\nSeleccionando el producto a comprar\n");
        Objeto producto = GestorInventario.seleccionarObjeto(productos);

        if (producto.getCantidad() == 0) {
            throw new RuntimeException("No hay existencia de este producto.");
        }

        System.out.println("\n¿Qué cantidad deseas agregar?\n");
        int cantidad = Validaciones.validarPositivo();

        if (producto.getCantidad() < cantidad) {
            throw new RuntimeException("No está la cantidad suficiente.");
        }

        compra.agregarAlCarro(producto, cantidad);
        GestorObjetos.decrementarCantidad(producto, cantidad);
    }

    // Para eliminar un objeto al carro, y devolver existencia.
    private static void eliminarObjeto(Boleta compra) {
        if (compra.getCantidadProductos() == 0) {
            throw new NullPointerException("No hay objetos en el carro.");
        }
        compra.imprimirCarro();

        System.out.println("\nSelecciona el producto a eliminar\n");
        int indiceProducto = Validaciones.validarInt(0,
                compra.getCantidadProductos()-1);
        Objeto eliminado = compra.seleccionarObjeto(indiceProducto);

        compra.removerDelCarro(eliminado);
        GestorObjetos.aumentarCantidad(eliminado, 1);
    }

    // Guarda la boleta en el directorio de boletas.
    private static void terminarventa(Boleta compra, Empresa seleccionada) throws Exception {
        System.out.println(compra.toString());
        GestorArchivos.crearArchivo("Boletas/"
                        + compra.getFechaHora() + ".txt",
                compra.toString());
        seleccionada.setFondos(seleccionada.getFondos() + compra.getTotal());
    }

    // Imprime una boleta seleccionada
    private static void imprimirBoleta() throws Exception {
        System.out.println(abrirBoleta());
    }

    // Selecciona una boleta.
    private static String abrirBoleta() throws Exception {
        String[] boletas = GestorArchivos.listaArchivos("Boletas");
        GestorArchivos.mostrarListaArchivos("Boletas");

        System.out.println("\nSeleccione el índice de la boleta.");
        int indiceBoleta = Validaciones.validarInt(0, boletas.length-1);

        String boleta = GestorArchivos.leerArchivo("Boletas/"
                + boletas[indiceBoleta]);

        return boleta;
    }

}
