package Reinas;

import static java.lang.Math.abs;

public class Celda {

    private int fila;
    private int columna;


    public Celda(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }


    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean celdasEnConflicto(Celda otra){

        int distancia_fila = abs(this.getFila() - otra.getFila() );
        int distancia_columna = abs(this.getColumna() - otra.getColumna() );

        if(distancia_fila == 0 || distancia_columna == 0 || distancia_columna == distancia_fila){
            return true;
        }else
            return false;
    }
}
