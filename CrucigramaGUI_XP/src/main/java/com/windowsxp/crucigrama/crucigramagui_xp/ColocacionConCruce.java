package com.windowsxp.crucigrama.crucigramagui_xp;

public class ColocacionConCruce extends EstrategiaColocacion {

    @Override
    protected boolean intentarColocarEnPosicion(String palabra, int fila, int col, Crucigrama crucigrama) {
        char letra = crucigrama.matriz[fila][col];
        for (int k = 0; k < palabra.length(); k++) {
            if (letra == palabra.charAt(k)) {
                int filaInicio = fila - k;
                int colInicio = col - k;

                if (crucigrama.puedeColocarseVertical(filaInicio, col, palabra)) {
                    crucigrama.colocarVertical(filaInicio, col, palabra);
                    return true;
                }
                if (crucigrama.puedeColocarseHorizontal(fila, colInicio, palabra)) {
                    crucigrama.colocarHorizontal(fila, colInicio, palabra);
                    return true;
                }
            }
        }
        return false;
    }
}
