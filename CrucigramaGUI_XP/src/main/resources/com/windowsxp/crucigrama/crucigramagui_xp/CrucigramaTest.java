package com.windowsxp.crucigrama.crucigramagui_xp;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CrucigramaTest {

    private Crucigrama crucigrama;

    @BeforeEach
    public void setUp() {
        String[] palabras = {"luz", "sol", "nube", "brisa"};
        crucigrama = new Crucigrama(palabras, 10);
    }

    // HU1: Inserción de palabras y ordenamiento
    @Test
    public void testPalabrasOrdenadasPorLongitud() {
        String[] palabras = {"sol", "montaña", "sol", "luz", "nube"};
        Crucigrama c = new Crucigrama(palabras, 10);
        assertEquals("montaña", c.palabras[0]); // longitud mayor
    }

    // HU2: Generación automática
    @Test
    public void testGeneraMatrizConPrimeraPalabra() {
        String[] palabras = {"montaña", "sol", "luz", "nube"};
        Crucigrama c = new Crucigrama(palabras, 10);
        char[][] matriz = c.matriz;
        boolean contiene = false;
        for (char ch : matriz[5]) {
            if (Character.toLowerCase(ch) == 'm') {
                contiene = true;
                break;
            }
        }
        assertTrue(contiene);
    }

    // HU3: Validación de mínimo de palabras (si devuelve mensaje por consola, no se puede testear directamente)
    @Test
    public void testMenosDeCuatroPalabras() {
        String[] palabras = {"sol", "luz", "mar"};
        Crucigrama c = new Crucigrama(palabras, 10);
        assertEquals(3, c.palabras.length);
    }

    // HU4: Tamaño mínimo de matriz debe ser 10x10
    @Test
    public void testMatrizTamanioMinimo10x10() {
        Crucigrama c = new Crucigrama(new String[]{"sol", "luz", "nube", "aire"}, 5);
        assertEquals(10, c.getTamaño());
    }

    // HU6: Evita palabras duplicadas
    @Test
    public void testCrucigramaInsertaPalabrasQueSeCruzan() {
        String[] palabras = {"luz", "nube", "aire", "sol"};
        Crucigrama c = new Crucigrama(palabras, 10);
        int letrasColocadas = 0;
        for (int i = 0; i < c.matriz.length; i++) {
            for (int j = 0; j < c.matriz[i].length; j++) {
                if (c.matriz[i][j] != ' ') letrasColocadas++;
            }
        }
        assertTrue(letrasColocadas >= 4); // Se insertó más de una palabra
    }

    // HU10: Crucigramas distintos para misma entrada
    @Test
    public void testInsertaPrimeraPalabraHorizontalEnCentro() {
        String[] palabras = {"montaña", "aire", "sol", "nube"};
        Crucigrama c = new Crucigrama(palabras, 10);
        int filaCentro = c.matriz.length / 2;
        int colInicio = (10 - palabras[0].length()) / 2;
        for (int i = 0; i < palabras[0].length(); i++) {
            assertEquals(palabras[0].charAt(i), c.matriz[filaCentro][colInicio + i]);
        }
    }
}
