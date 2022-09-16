
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {

    @Test
    @DisplayName("Verificar la conversión correcta de HashMap a String")
    public void testInventarioAString() {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("Test1", 1);
        test.put("Test2", 2);
        String esperado = "Test1:1\nTest2:2";
        assertEquals(esperado, main.inventarioAString(test));
    }

    @Test
    @DisplayName("Verificar comportamiento de la funcion dado un inventario vacio")
    public void testInventarioAStringInvVacio() {
        HashMap<String, Integer> test = new HashMap<>();
        String esperado = "";
        assertEquals(esperado, main.inventarioAString(test));
    }

    // Test relacionados a cargar inventarios

    @Test
    @DisplayName("Comprobar si se puede pasar de string a hashmap en condiciones normales")
    public void testCargarInventarioDefault() {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("Test1", 1);
        test.put("Test2", 1);
        assertEquals(test, main.cargarInventario("Inventarios/[PruebasUnitarias].txt"));
    }

    @Test
    @DisplayName("Comprobar si se puede pasar de string a hashmap con un archivo vacio")
    public void testCargarInventarioDefaultVacio() {
        HashMap<String, Integer> test = new HashMap<>();
        assertEquals(test, main.cargarInventario("Inventarios/[PruebasUnitariasVacio].txt"));
    }

    @Test
    @DisplayName("Cargar inventario con ruta nula")
    public void testCargarInventarioRutaNula() {
        assertThrows(NullPointerException.class,
                () -> main.cargarInventario(null),
                "La ruta del inventario es nula.");
    }

    // Test relacionados a guardar inventarios
    @Test
    @DisplayName("Para cuando la ruta es nula")
    void testEscribirArchivoRutaNula() {
        String contenido = "Item:1";
        String ruta = null;
        assertThrows(InvalidPathException.class,
                () -> main.escribirArchivo(contenido, ruta),
                "La ruta es nula");
    }

    @Test
    @DisplayName("Para cuando el archivo es nulo")
    void testEscribirArchivoNulo() {
        String contenido = null;
        String ruta = "Inventarios/[PruebasUnitariasNulo].txt";
        assertThrows(NullPointerException.class,
                () -> main.escribirArchivo(contenido, ruta),
                "El archivo es nulo");
    }

    // Test relacionados a lectura
    @Test
    @DisplayName("El archivo no existe")
    public void testLeerArchivoPorLineas() {
        String ruta = "No existe";
        assertThrows(IOException.class,
                () -> main.leerArchivoPorLineas(ruta),
                "El archivo no existe");
    }

    @Test
    @DisplayName("El inventario es nulo, por ende, no se podría leer")
    public void testMostrarInventarioNulo() {
        HashMap<String, Integer> inventario = null;
        assertThrows(NullPointerException.class,
                () -> main.mostrarInventario(inventario),
                "El inventario es nulo.");
    }







}