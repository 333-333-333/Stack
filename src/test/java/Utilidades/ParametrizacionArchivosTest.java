package Utilidades;

import Clases.Objeto;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Utilidades.Parametrizador.aJson;
import static org.junit.jupiter.api.Assertions.*;

class ParametrizacionArchivosTest {

    private Gson g;

    @BeforeEach
    void init() {
        g = new Gson();
    }

    @AfterEach
    void tear() {
        g = null;
    }

    @DisplayName("[aJson] Se testea la felicidad del método.")
    @Test
    void aJsonTest_fidelidad() {
        Objeto test = new Objeto("test",
                               "proveedorTest",
                                    "Test descripcion",
                            2,
                                      990);

        String esperado ="{\"Nombre\":\"test\","
                        +"\"Desc\":\"Test descripcion\","
                        +"\"Proveedor\":\"proveedorTest\","
                        +"\"ID\":990,"
                        +"\"CantidadCritica\":2,"
                        +"\"Cantidad\":1}";

        assertEquals(esperado, aJson(test));
    }



}