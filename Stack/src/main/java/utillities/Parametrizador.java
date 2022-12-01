package utillities;

import com.google.gson.Gson;

public class Parametrizador {

    // Para guardar un objeto a .json
    public static String aJson(Object objeto) {
        Gson g = new Gson();
        return g.toJson(objeto);
    }

}
