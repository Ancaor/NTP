import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EjemplosUsoIntStream {
    //datos para trabajar

    private int[] valores;

    public EjemplosUsoIntStream(int numeroValores){
        valores = new int[numeroValores];
        Random generador=new Random();
        generador.setSeed(0);
        for(int i=0; i < numeroValores; i++){
            valores[i]=generador.nextInt(101);
        }
    }

    //Listado de valores en modo imperativo

    public void mostrarValoresImerativo(){
        for(int i=0; i < valores.length; i++){
            System.out.printf("%d ",valores[i]);
        }
        System.out.println();
    }

    //Listado de valores en modo funcional

    public void mostrarValoresFuncional(){
        //creacion de flujo
        IntStream flujo = IntStream.of(valores);

        //se define la operacion a realizar sobre cada valor
        IntConsumer operacion = valor -> System.out.printf("%d ", valor);

        //se usa el flujo
        flujo.forEach(operacion);
        System.out.println();
    }

    public void mostrarValoresFuncional2(){
        IntStream.of(valores).
                forEach(valor->System.out.printf("%d ",valor));
    }

    //como el argumento solo s usa, el argumento queda implicito y puedo omitirlo:
    //En la siguiente funcion coge el argumento que le llega y le aplica la funcion println
    //Se usa println porque printf requiere que se le pase la variable y el formato.

    public void mostrarValoresFuncional3(){
        IntStream.of(valores).forEach(System.out::println);
    }


    //contar valores de la coleccion
    public long contarValoresFuncional(){
        return IntStream.of(valores).count();
    }

    //sumar valores en modo imperativo
    public long sumarValoresImperativo(){
        long suma=0;
        for(int i=0; i < valores.length ; i++){
            suma = suma+valores[i];
        }
        return suma;
    }

    //sumar valores en modo programacion funcional
    public long sumarValoresFuncional(){
        return IntStream.of(valores).sum();
    }

    //sumar valores funcional con reduce

    public long sumarValoresFuncionalReduce(){
        return IntStream.of(valores).reduce(0,(x,y)-> x+y); // el 0 es la identidad para la suma, si fuera multiplicacion el 0 deberia ser un 1
    }

    //producto con reduce
    public double multiplicarValoresFuncional(){
        return IntStream.of(valores).asDoubleStream().reduce(1.0,(x,y)-> x*y);
    }

    //suma de valores al cuadrado
    public  double sumaAlCuadradoFuncional(){
        return IntStream.of(valores).asDoubleStream().reduce(0,(x,y)-> x+(y*y));
    }

    public int minimoFuncional(){
        return IntStream.of(valores).reduce(valores[0],(x,y)->{
            if(x < y ) return x;
            else return y;
        });
    }

    public int minimoFuncionalMetodo(){
        OptionalInt resultado =  IntStream.of(valores).min();
        return resultado.orElse(-1);
    }

    public int calcularMaximoFuncional(){
        return IntStream.of(valores).max().getAsInt();
    }

    //flitrado de valores pares
    public int[] obtenerParesFuncional(){
        IntStream flujo = IntStream.of(valores);

        //creo predicado para el filtrado
        IntPredicate predicado = x -> x%2 == 0;

        IntStream flujo2 = flujo.filter(predicado);
        return flujo2.toArray();
    }

    public int[] obtenerParesFuncional2(){
        return IntStream.of(valores).filter(x -> x%2 == 0).toArray();
    }

    public int[] predicadosComplejos(){
        //predicado para pares
        IntPredicate predicado1 = x -> x%2 == 0;
        IntPredicate predicado2 = x -> x > 5;
        IntPredicate total = predicado1.and(predicado2);
        return IntStream.of(valores).filter(total).toArray();
    }

    //operacin para fultrar pares, multiplucarlos por factor y ordenar

    public double[] filtrarMultiplicarOrdenar(double factor){
        return IntStream.of(valores).filter(x -> x%2 == 0).mapToDouble(x -> x*factor).sorted().toArray();
    }

    //main para pruebas
    public static void main (String args[]){
        EjemplosUsoIntStream objeto = new EjemplosUsoIntStream(20);

        //mostramos valores con metodo imperativo
        objeto.mostrarValoresImerativo();

        //mostramos valores con metodo funcional
        objeto.mostrarValoresFuncional();

        //mostrar valores en metodo funcional 2
        objeto.mostrarValoresFuncional2();

        System.out.println("Suma de valores ");
        System.out.println("Suma funcional : " + objeto.sumarValoresFuncional());
        System.out.println("Suma funcional reduce : " + objeto.sumarValoresFuncionalReduce());
        System.out.println("Suma funcional cuadrado: "+ objeto.sumaAlCuadradoFuncional());
    }

}
