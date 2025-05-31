package com.windowsxp.crucigrama.crucigramagui_xp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CrucigramaTest {

    private Crucigrama crucigrama;

    @BeforeEach
    public void setUp() {
        String[] palabras = {"casa", "sol", "luna", "estrella"};
        crucigrama = new Crucigrama(palabras, 10);
    }

    @Test
    public void testTamañoMinimo() {
        Crucigrama cruci = new Crucigrama(new String[]{"a", "b", "c", "d"}, 5);
        assertEquals(10, cruci.getTamaño()); // Debe ajustarse a mínimo 10
    }

    @Test
    public void testTamañoPersonalizado() {
        Crucigrama cruci = new Crucigrama(new String[]{"uno", "dos", "tres", "cuatro"}, 15);
        assertEquals(15, cruci.getTamaño());
    }

    @Test
    public void testPrimeraPalabraCentrada() {
        String[] palabras = {"palabra", "otra", "mas", "aqui"};
        Crucigrama cruci = new Crucigrama(palabras, 15);
        char[][] matriz = cruci.matriz;
        int filaCentral = 15 / 2;
        int inicio = (15 - palabras[0].length()) / 2;

        for (int i = 0; i < palabras[0].length(); i++) {
            assertEquals(palabras[0].charAt(i), matriz[filaCentral][inicio + i]);
        }
    }

    @Test
    public void testOrdenamientoPorLongitud() {
        String[] palabras = {"sol", "universo", "luna", "estrella"}; //f
        Crucigrama cruci = new Crucigrama(palabras, 12);
        String[] ordenado = cruci.palabras;

        assertTrue(ordenado[0].length() >= ordenado[1].length());
        assertTrue(ordenado[1].length() >= ordenado[2].length());
        assertTrue(ordenado[2].length() >= ordenado[3].length());
    }

    @Test

    public void testColocarPalabraMuyLarga() {
        String[] palabras = {"casa", "perro", "sol", "gato"};
        Crucigrama crucigrama = new Crucigrama(palabras, 10); // Palabras válidas
        boolean resultado = crucigrama.colocarPalabra("UMSSLaMejorUniversidad");
        assertFalse(resultado, "La palabra es demasiado larga para el tablero y no debería colocarse");
    }


    @Test
    public void testColocacionExitosaDeVariasPalabras() {
        String[] palabras = {"sol", "luna", "estrella", "astro"};
        Crucigrama cruci = new Crucigrama(palabras, 12);
        char[][] matriz = cruci.matriz;
        int letrasEncontradas = 0;
        for (char[] fila : matriz) {
            for (char c : fila) {
                if (Character.isLetter(c)) {
                    letrasEncontradas++;
                }
            }
        }
        assertTrue(letrasEncontradas >= 15);
    }


}
