package com.windowsxp.crucigrama.crucigramagui_xp;

public class GestordePalabras {

    public void ordenarPorLongitud(String[] palabras) {
        boolean intercambiado;
        do {
            intercambiado = false;
            for(int i = 0; i < palabras.length - 1; i++) {
                if(palabras[i].length() < palabras[i+1].length()) {
                    String temp = palabras[i];
                    palabras[i] = palabras[i+1];
                    palabras[i+1] = temp;
                    intercambiado = true;
                }
            }
        } while(intercambiado);
    }
}
