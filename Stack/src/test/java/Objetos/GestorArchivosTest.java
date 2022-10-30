package Utilidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GestorArchivosTest {


    @DisplayName("[crearArchivo] Se comprueba de que el archivo sea creado exitosamente.")
    @Test
    void crearArchivo() throws Exception {

        GestorArchivos.crearArchivo("src/test/java/Utilidades/test1.txt", "asjdashdj");
        Path directorio = Paths.get("src/test/java/Utilidades/test1.txt");
        assertTrue(Files.exists(directorio), "El archivo NO se ha creado exitosamente.");
    }

    @DisplayName("[crearDirectorio] Se comprueba de que el directorio sea creado exitosamente.")
    @Test
    void crearDirectorio() throws Exception {
        GestorArchivos.crearDirectorio("src/test/java/Utilidades/test2/");
        Path directorio = Paths.get("src/test/java/Utilidades/test2/");
        assertTrue(Files.exists(directorio), "El directorio NO se ha creado exitosamente.");
    }

    @DisplayName("[leerArchivo] Se comprueba que el string devuelto contenga realmente el texto correspondiente al archivo")
    @Test
    void leerArchivo() throws Exception {

        GestorArchivos.crearArchivo("src/test/java/Utilidades/test3.txt", "completo italiano");
        Path directorio = Paths.get("src/test/java/Utilidades/test3.txt");

        assertEquals("completo italiano",
                GestorArchivos.leerArchivo("src/test/java/Utilidades/test3.txt"),
                "El texto devuelto no corresponde con el contenido del archivo.");
    }

    @DisplayName("[listaArchivos] Se comprueba que la lista de archivos devuelta contenga todos los archivos correspondientes al directorio.")
    @Test
    void listaArchivos() throws Exception {

        String[] archivos = {"test3.txt", "test4", "test1.txt", "test2", "GestorArchivosTest.java"};
        String[] listaArchivos = GestorArchivos.listaArchivos("src/test/java/Utilidades/");

        assertTrue(Arrays.equals(listaArchivos, archivos), "La lista de archivos devuelta no corresponde con los archivos existentes en la ruta.");
    }

    @DisplayName("[verificarExistenciaDirectorio] Se comprueba que el directorio deseado exista en la ruta especificada.")
    @Test
    void verificarExistenciaDirectorio() throws Exception {

        GestorArchivos.crearDirectorio("src/test/java/Utilidades/test4/");
        assertTrue(GestorArchivos.verificarExistenciaDirectorio("src/test/java/Utilidades/test4/"));

    }
}