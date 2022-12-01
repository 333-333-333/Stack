package model;


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
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1, 1);
        this.boletaTest.agregarAlCarro(o);

        assertEquals(1,
                this.boletaTest.getCarro().size(),
                "No se han agregado más objetos al carro. ");
    }

// Tests removerDelCarro

    @DisplayName("[removerDelCarro] Comprueba si arroja error al estar vacío.")
    @Test
    void removerDelCarroErrorArregloVacio() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1, 1);

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> this.boletaTest.removerDelCarro(o),
                "No se enseña que el carro está vacío.");
    }

    @DisplayName("[removerDelCarro] Comprueba si arroja error al no contener objeto.")
    @Test
    void removerDelCarroObjetoNoEnCarro() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1, 1);
        Objeto o2 = new Objeto("Test2", "Test2", "Test2", 1, 1, 1);
        this.boletaTest.agregarAlCarro(o);

        assertThrows(IllegalArgumentException.class,
                () -> this.boletaTest.removerDelCarro(o2),
                "No se enseña que el carro no contiene el objeto.");
    }

    @DisplayName("[removerDelCarro] Comprueba fidelidad.")
    @Test
    void removerDelCarroFidelidad() {
        Objeto o = new Objeto("Test", "Test", "Test", 1, 1, 1);
        this.boletaTest.agregarAlCarro(o);
        this.boletaTest.removerDelCarro(o);

        assertEquals(0, this.boletaTest.getCarro().size());
    }



}