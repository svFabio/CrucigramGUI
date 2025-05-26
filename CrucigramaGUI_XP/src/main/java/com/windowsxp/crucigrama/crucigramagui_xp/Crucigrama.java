package com.windowsxp.crucigrama.crucigramagui_xp;

public class Crucigrama {
    char[][] matriz;
    private int tamaño;
    String[] palabras;

    public Crucigrama(String[] palabras, int tamanito) {
        if(tamanito < 10) {
            this.tamaño = 10;
        } else {
            this.tamaño = tamanito;
        }

        this.matriz = new char[tamaño][tamaño];
        this.palabras = palabras;
        inicializarMatriz();
        generarCrucigrama   ();
    }

    private void inicializarMatriz() {
        for(int i = 0; i < tamaño; i++) {
            for(int j = 0; j < tamaño; j++) {
                matriz[i][j] = ' ';
            }
        }
    }

    private void generarCrucigrama() {
        if(palabras.length < 4) {
            System.out.println("Error: Deben introducirse al menos 4 palabras.");
            return;
        }

        ordenarPalabrasPorLongitud(); // Ahora usa ordenación manual

        colocarPrimeraPalabra(palabras[0]);

        for(int i = 1; i < palabras.length; i++) {
            if(!colocarPalabra(palabras[i])) {
                System.out.println("No se pudo colocar la palabra: " + palabras[i]);
            }
        }
    }

    private void ordenarPalabrasPorLongitud() {
        // Implementación del método de burbuja para ordenar por longitud descendente
        boolean intercambiado;
        do {
            intercambiado = false;
            for(int i = 0; i < palabras.length - 1; i++) {
                if(palabras[i].length() < palabras[i+1].length()) {
                    // Intercambiar palabras
                    String temp = palabras[i];
                    palabras[i] = palabras[i+1];
                    palabras[i+1] = temp;
                    intercambiado = true;
                }
            }
        } while(intercambiado);
    }

    // Los demás métodos permanecen igual...
    private void colocarPrimeraPalabra(String palabra) {
        if(palabra.length() > tamaño){
            System.out.println("Error: la palabra "+palabra +" es demasiado larga para el tablero");
            return;
        }

        int fila = tamaño / 2;
        int inicio = (tamaño - palabra.length()) / 2;

        for(int i = 0; i < palabra.length(); i++) {
            matriz[fila][inicio + i] = palabra.charAt(i);
        }
    }

    private boolean colocarPalabra(String palabra) {
        // Intentar colocar con cruce
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                char letra = matriz[i][j];
                for (int k = 0; k < palabra.length(); k++) {
                    if (letra == palabra.charAt(k)) {
                        if (puedeColocarseVertical(i - k, j, palabra)) {
                            colocarVertical(i - k, j, palabra);
                            return true;
                        }
                        if (puedeColocarseHorizontal(i, j - k, palabra)) {
                            colocarHorizontal(i, j - k, palabra);
                            return true;
                        }
                    }
                }
            }
        }

        // Si no se puede colocar con cruce, intenta colocar en cualquier lugar libre (horizontal primero)
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (puedeColocarseHorizontal(i, j, palabra)) {
                    colocarHorizontal(i, j, palabra);
                    return true;
                }
                if (puedeColocarseVertical(i, j, palabra)) {
                    colocarVertical(i, j, palabra);
                    return true;
                }
            }
        }

        return false;
    }


    private boolean puedeColocarseVertical(int fila, int col, String palabra) {
        if(fila < 0 || fila + palabra.length() > tamaño) return false;
        for(int i = 0; i < palabra.length(); i++) {
            char c = matriz[fila + i][col];
            if(c != ' ' && c != palabra.charAt(i)) return false;
        }
        return true;
    }

    private boolean puedeColocarseHorizontal(int fila, int col, String palabra) {
        if(col < 0 || col + palabra.length() > tamaño) return false;
        for(int i = 0; i < palabra.length(); i++) {
            char c = matriz[fila][col + i];
            if(c != ' ' && c != palabra.charAt(i)) return false;
        }
        return true;
    }

    private void colocarVertical(int fila, int col, String palabra) {
        for(int i = 0; i < palabra.length(); i++) {
            matriz[fila + i][col] = palabra.charAt(i);
        }
    }

    private void colocarHorizontal(int fila, int col, String palabra) {
        for(int i = 0; i < palabra.length(); i++) {
            matriz[fila][col + i] = palabra.charAt(i);
        }
    }

    public void imprimirCrucigrama() {
        for(int i = 0; i < tamaño; i++) {
            for(int j = 0; j < tamaño; j++) {
                if(matriz[i][j] == ' ') {
                    System.out.print(". ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
}


