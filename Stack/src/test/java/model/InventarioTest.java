package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    private Inventario InventarioTest;

    @BeforeEach
    void init() {
        this.InventarioTest = new Inventario();
    }

// Tests agregarObjeto
    @DisplayName("[agregarObjeto] Arroja error al ingresar objeto nulo.")
    @Test
    void agregarObjetoNulo() {
        assertThrows(NullPointerException.class,
                () -> this.InventarioTest.agregarObjeto(null),
                "Error al tirar una excepcion por el objeto nulo.");
    }

    @DisplayName("[agregarObjeto] Comprueba fidelidad (Aumenta tamaño)")
    @Test
    void agregarObjetoFidelidadAumentaInventario() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);
        this.InventarioTest.agregarObjeto(o);

        assertEquals(1,
                this.InventarioTest.getObjetos().size());
    }

    @DisplayName("[agregarObjeto] Comprueba fidelidad (Contiene objeto)")
    @Test
    void agregarObjetoFidelidadContieneObjeto() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);
        this.InventarioTest.agregarObjeto(o);

        assertTrue(this.InventarioTest.getObjetos().contains(o));
    }

// Tests eliminarObjeto
    @DisplayName("[eliminarObjeto] Arroja error al no haber objetos")
    @Test
    void eliminarObjetoSinObjetos() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.InventarioTest.eliminarObjeto(o),
                "Error al arrojar la excepcion por estar sin objetos.");
    }

    @DisplayName("[eliminarObjeto] Arroja error al pasar un objeto nulo.")
    @Test
    void eliminarObjetoNulo() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);
        this.InventarioTest.agregarObjeto(o);

        assertThrows(NullPointerException.class,
                () -> this.InventarioTest.eliminarObjeto(null),
                "Error al arrojar la excepcion por tratar de eliminar un null.");
    }

    @DisplayName("[eliminarObjeto] Arroja error por objeto sin existencia")
    @Test
    void eliminarObjetoInexistente() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);
        Objeto p = new Objeto("test2", "test2", "test2", 100, 100, 100);
        this.InventarioTest.agregarObjeto(o);

        assertThrows(IllegalArgumentException.class,
                () -> this.InventarioTest.eliminarObjeto(p),
                "Error al arrojar excepcion por eliminar objeto que no está");
    }

    @DisplayName("[eliminarObjeto] Comprueba fidelidad (Decrementa tamaño)")
    @Test
    void eliminarObjetoFidelidadDecrementa() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);
        this.InventarioTest.agregarObjeto(o);

        this.InventarioTest.eliminarObjeto(o);
        assertEquals(0, this.InventarioTest.getObjetos().size());
    }

    @DisplayName("[eliminarObjeto] Comprueba fidelidad (Elimina objeto)")
    @Test
    void eliminarObjetoElimina() {
        Objeto o = new Objeto("test", "test", "test", 100, 100, 100);
        this.InventarioTest.agregarObjeto(o);
        this.InventarioTest.eliminarObjeto(o);

        assertFalse(this.InventarioTest.getObjetos().contains(o));
    }

// Test ordenarPorPrecioMenosMas
    @DisplayName("[ordenarPrecioMenosMas] Arroja error por inventario vacio.")
    @Test
    void ordenarPrecioMenosMasArregloVacio() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.InventarioTest.ordenarPorPrecioMenosMas(),
                "Error al arrojar excepcion por inventario vacio.");
    }

// Test ordenarPorPrecioMenosMas
    @DisplayName("[ordenarPorCantiaddMenosMas] Arroja error por inventario vacio.")
    @Test
    void ordenarPorCantidadArregloVacio() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.InventarioTest.ordenarPorCantidadMenosMas(),
                "Error al arrojar excepcion por inventario vacio.");
    }

// Test ordenarPorNombreAZ
    @DisplayName("[ordenarPorNombreAZ] Arroja error por inventario vacio.")
    @Test
    void ordenarPorNombreAZVacio() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.InventarioTest.ordenarPorNombreAZ(),
                "Error al arrojar excepcion por inventario vacio.");
    }

}