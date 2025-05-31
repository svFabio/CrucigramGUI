package com.windowsxp.crucigrama.crucigramagui_xp;

public abstract class EstrategiaColocacion {

    public boolean colocarPalabra(String palabra, Crucigrama crucigrama) {
        for (int i = 0; i < crucigrama.getTamaño(); i++) {
            for (int j = 0; j < crucigrama.getTamaño(); j++) {
                if (intentarColocarEnPosicion(palabra, i, j, crucigrama)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract boolean intentarColocarEnPosicion(String palabra,
                                                         int fila, int col, Crucigrama crucigrama);

}
