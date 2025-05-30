package com.windowsxp.crucigrama.crucigramagui_xp;

public class Main {
    public static void main(String[] args) {
        String[] palabras = {
                "proyecto",
                "codigo",
                "crucigrama",
                "matriz",
                "programa"
        };

        int tamañoTablero = 15;

        Crucigrama crucigrama = new Crucigrama(palabras, tamañoTablero);
        System.out.println("Crucigrama generado:");
        crucigrama.imprimirCrucigrama();
    }
}

