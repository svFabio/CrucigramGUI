package com.windowsxp.crucigrama.crucigramagui_xp;

public class ColocacionLibre extends EstrategiaColocacion {

    @Override
    protected boolean intentarColocarEnPosicion(String palabra, int fila, int col, Crucigrama crucigrama) {
        if (crucigrama.puedeColocarseHorizontal(fila, col, palabra)) {
            crucigrama.colocarHorizontal(fila, col, palabra);
            return true;
        }
        if (crucigrama.puedeColocarseVertical(fila, col, palabra)) {
            crucigrama.colocarVertical(fila, col, palabra);
            return true;
        }
        return false;
    }
}
