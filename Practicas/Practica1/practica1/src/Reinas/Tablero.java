package Reinas;

import java.util.ArrayList;

public class Tablero {

    private int dimension;
    private ArrayList<Celda> contenido;

    /**
     * Constructor de la clase Tablero
     * @param dimension dimension del tablero
     */

    public Tablero(int dimension) {
        this.dimension = dimension;
        contenido = new ArrayList<>();
    }

    /**
     * Metodo de copia del tablero
     * @param other tablero del que vamos a copiar su contenido.
     * @return
     */
    public static Tablero copy( Tablero other ) {
        Tablero newTablero  = new Tablero(other.dimension);
        newTablero.contenido = (ArrayList<Celda>) other.contenido.clone();

        return newTablero;
    }

    /**
     * Coloca una reina en el tablero sin comprobar nada
     * @param fila fila del tablero donde se colocara
     * @param columna columna del tablero donde se colocara
     */
    public void ponerReina(int fila, int columna){
        Celda celdaReina = new Celda(fila,columna);
        contenido.add(celdaReina);
    }

    /**
     * Busca conflictos entre una reina y el resto de reinas del tablero
     * @param pos posicion de la reina que se va a colocar en el tablero
     * @return true si la posicion es segura, false en caso contrario
     */
    public boolean posicionSegura(Celda pos){

        long conflictos = contenido.stream().map(celda -> celda.celdasEnConflicto(pos)).filter(flag -> flag == true).count();

        if(conflictos == 0)
            return true;
        else return false;
    }

    /**
     * Devuelve un string con el formato correcto del tablero de ajedrez
     * @return
     */
    public String toString(){

        char[] tableroChars = new char[dimension*dimension];//tablero.toCharArray();
        for(int i=0; i < dimension*dimension; i++){
            tableroChars[i] = 'X';
        }

        contenido.forEach((celda) -> {
            int index = celda.getFila() * dimension + celda.getColumna();
            tableroChars[index] = 'R';
        });

        String tablero= "";

        for (int i=0; i < this.dimension; i ++){
            for (int j=0; j < this.dimension; j++){
                tablero = tablero + tableroChars[i*this.dimension + j];
            }
            tablero = tablero + "\n";
        }

        return tablero;
    }








}
