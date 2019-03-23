package Reinas;

import java.util.ArrayList;
import java.util.List;

public class Buscador {

    private int dimension;

    public Buscador(int dimension){
        this.dimension = dimension;
    }


    public ArrayList<Tablero> ubicarReina(int fila){



        if(fila == -1){
            System.out.println("Caso base");
            Tablero tablero = new Tablero(dimension);
            ArrayList<Tablero> soluciones = new ArrayList<>();
            soluciones.add(tablero);

            return soluciones;
        }else{
            System.out.println("Caso inductivo, fila : " + fila);

            ArrayList<Tablero> soluciones_anteriores = ubicarReina(fila-1);
            ArrayList<Tablero> soluciones = new ArrayList<>();

            soluciones_anteriores.forEach(tablero -> {

                for(int columna=0; columna < dimension; columna++){

                    System.out.println("Columna : " + columna);

                    Celda celda = new Celda(fila,columna);

                    if(tablero.posicionSegura(celda)){

                        Tablero aux = Tablero.copy(tablero);

                        System.out.println("Posicion segura fila :" + fila + " , columna : "+ columna);
                        aux.ponerReina(fila,columna);
                        System.out.println(tablero.toString());
                        soluciones.add(aux);

                    }



                }

            });



            System.out.println("Soluciones size : " + soluciones.size() );
            return soluciones;
        }



    }

    public ArrayList<Tablero> resolver(){
        return ubicarReina(dimension-1);
    }

    public static void main( String[] args){
        Buscador b = new Buscador(8);

        ArrayList<Tablero> resolver = b.resolver();

        System.out.println(resolver.size());

    }

}
