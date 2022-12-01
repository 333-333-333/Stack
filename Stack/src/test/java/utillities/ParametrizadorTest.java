package utillities;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametrizadorTest {

    @DisplayName("[aJson] Se comprueba que el json entregado corresponda al objeto deseado.")
    @Test
    void aJson() {
        int[] arr = {1,2,3,4,5,6,7};

        Gson g = new Gson();
        assertEquals(g.toJson(arr), Parametrizador.aJson(arr));
    }
}