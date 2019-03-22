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

    public void ponerReina(int fila, int columna){
        Celda celdaReina = new Celda(fila,columna);
        contenido.add(celdaReina);
    }

    public boolean posicionSegura(Celda pos){

        long conflictos = contenido.stream().map(celda -> celda.celdasEnConflicto(pos)).filter(flag -> flag == true).count();

        if(conflictos == 0)
            return true;
        else return false;
    }

    public String toString(){
        String tablero = "";
        for(int i=0; i < dimension*dimension; i++){
            tablero = tablero + "X";
        }
        char[] tableroChars = tablero.toCharArray();

        contenido.forEach((celda) -> {
            int index = celda.getFila() * dimension + celda.getColumna();
            tableroChars[index] = 'R';
        });
        tablero= "";
        for (int i=0; i < this.dimension; i ++){
            for (int j=0; j < this.dimension; j++){
                tablero = tablero + tableroChars[i*this.dimension + j];
            }
            tablero = tablero + "\n";
        }

        return tablero;
    }



    public static void main( String[] args){
        Tablero a = new Tablero(4);
        a.ponerReina(1,1);
        System.out.println(a.toString());
    }







}
