import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {

    @Test
    @DisplayName("Verificar la conversión correcta de HashMap a String")
    public void testInventarioAString() {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("Test1",1);
        test.put("Test2",2);
        String esperado ="Test1:1\nTest2:2";
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
        test.put("Test1",1);
        test.put("Test2",1);
        assertEquals(test, main.cargarInventario("Inventarios/[PruebasUnitarias].txt"));
    }

    @Test
    @DisplayName("Comprobar si se puede pasar de string a hashmap con un archivo vacio")
    public void testCargarInventarioDefaultVacio() {
        HashMap<String, Integer> test = new HashMap<>();
        assertEquals(test, main.cargarInventario("Inventarios/[PruebasUnitariasVacio].txt"));
    }

    @Test
    void escribirArchivo() {

        
    }
    // Test relacionados a guardar inventarios




}