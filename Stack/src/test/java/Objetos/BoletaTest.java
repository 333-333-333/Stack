package Objetos;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoletaTest {

    private Boleta boletaTest;

    @BeforeEach
    void init() {
        this.boletaTest = new Boleta();
    }

// Tests agregarAlCarro

    @DisplayName("[agregarAlCarro] Se comprueba la fidelidad del método.")
    @Test
    void agregarAlCarroFidelidad() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1);
        this.boletaTest.agregarAlCarro(o, 10);

        assertEquals(10,
                this.boletaTest.getCantidadProductos(),
                "No se han agregado más objetos al carro. ");
    }

// Tests removerDelCarro

    @DisplayName("[removerDelCarro] Comprueba si arroja error al estar vacío.")
    @Test
    void removerDelCarroErrorArregloVacio() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1);

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.boletaTest.removerDelCarro(o),
                "No se enseña que el carro está vacío.");
    }

    @DisplayName("[removerDelCarro] Comprueba si arroja error al no contener objeto.")
    @Test
    void removerDelCarroObjetoNoEnCarro() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1);
        Objeto o2 = new Objeto("Test2", "Test2", "Test2", 1, 1);
        this.boletaTest.agregarAlCarro(o, 5);

        assertThrows(IllegalArgumentException.class,
                () -> this.boletaTest.removerDelCarro(o2),
                "No se enseña que el carro no contiene el objeto.");
    }

    @DisplayName("[removerDelCarro] Comprueba fidelidad.")
    @Test
    void removerDelCarroFidelidad() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1);
        this.boletaTest.agregarAlCarro(o, 5);
        this.boletaTest.removerDelCarro(o);

        assertEquals(4, this.boletaTest.getCantidadProductos());
    }

// Tests seleccionarObjeto.
    @DisplayName("[seleccionarObjeto] Tira error en índice inválido.")
    @Test
    void seleccionarObjetoIndiceInvalido() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1);
        this.boletaTest.agregarAlCarro(o, 5);

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.boletaTest.seleccionarObjeto(999),
                "Debiese arrojar error al seleccionar índice inválido.");
    }

    @DisplayName("[seleccionarObjeto] Comprueba fidelidad.")
    @Test
    void seleccionarObjetoFidelidad() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1);
        this.boletaTest.agregarAlCarro(o, 5);

        Objeto p = new Objeto("Test2", "Test2", "Test2", 1, 1);
        this.boletaTest.agregarAlCarro(p, 5);

        assertEquals(p,
                this.boletaTest.seleccionarObjeto(8),
                "No detecta a los objetos como iguales.");
    }

// Tests calcularTotal
    @DisplayName("[calcularTotal] Tira error frente a Overflow.")
    @Test
    void calcularTotalOverflow() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, Integer.MAX_VALUE - 1);
        this.boletaTest.agregarAlCarro(o, 5);

        assertThrows(RuntimeException.class,
                () -> this.boletaTest.calcularTotal(),
                "No detecta el overflow.");
    }

    @DisplayName("[calcularTotal] Comprueba fidelidad.")
    @Test
    void calcularTotalFidelidad() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 500);
        this.boletaTest.agregarAlCarro(o, 5);
        this.boletaTest.calcularTotal();

        assertEquals(2500,
                this.boletaTest.getTotal(),
                "El total calculado no es el correspondido.");
    }



}