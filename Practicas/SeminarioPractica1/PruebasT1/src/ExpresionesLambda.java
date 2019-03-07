import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

public class ExpresionesLambda {

//main para probar
    public static void main(String args[]){
        //expresion lambda para sumar valores
        IntBinaryOperator exp1 = (int x, int y) -> {
            return x + y;
        };

        int var = exp1.applyAsInt(2,3);
        System.out.println(var);

        //expresion lambda para sumar doubles

        DoubleBinaryOperator exp2 = (double x, double y) -> x + y;

        double var2 = exp2.applyAsDouble(2.5,3.5);
        System.out.println(var2);

        //otra expresion lambda

        Consumer<Integer> exp3 = valor -> System.out.println("valor: " + valor);
        exp3.accept(12);

        //expresion que encaja con ruanable

        Runnable exp4 = () -> System.out.println("Hola que tal");
        exp4.run();

        //otra

        IntBinaryOperator exp5 = new IntBinaryOperator() {
            @Override
            public int applyAsInt(int i, int i1) {
                return i+i1;
            }
        };
    }
}
