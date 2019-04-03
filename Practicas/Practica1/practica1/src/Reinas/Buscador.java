package Reinas;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Buscador {

    private int dimension;


    /**
     * Constructor de Buscador que indica la dimension del tablero
     * @param dimension
     */
    public Buscador(int dimension){
        this.dimension = dimension;
    }


    /**
     * Metodo recursivo que resuelve el problema de las 8 reinas
     * @param fila fila por la que se empieza a calcular
     * @return todos los tableros solucion
     */
    public ArrayList<Tablero> ubicarReina(int fila){

        if(fila == -1){
            Tablero tablero = new Tablero(dimension);
            ArrayList<Tablero> soluciones = new ArrayList<>();
            soluciones.add(tablero);

            return soluciones;
        }else{

            ArrayList<Tablero> soluciones_anteriores = ubicarReina(fila-1);
            ArrayList<Tablero> soluciones = new ArrayList<>();

            soluciones_anteriores.forEach(tablero -> IntStream.range(0, dimension).
                    mapToObj(columna -> new Celda(fila, columna)).
                    forEach(celda -> {

                        if (tablero.posicionSegura(celda)) {

                            Tablero aux = Tablero.copy(tablero);
                            aux.ponerReina(fila, celda.getColumna());
                            soluciones.add(aux);
                        }
                    }));

            return soluciones;
        }



    }

    public ArrayList<Tablero> resolver(){
        return ubicarReina(dimension-1);
    }

    public static void main( String[] args){
        Buscador b = new Buscador(14);

        ArrayList<Tablero> resolver = b.resolver();

        System.out.println(resolver.size());

        System.out.println(resolver.get(0).toString());
        System.out.println(resolver.get(1).toString());


    }

}
