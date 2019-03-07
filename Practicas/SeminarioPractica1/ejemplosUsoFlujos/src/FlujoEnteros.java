import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlujoEnteros {
    public static void main (String args[]){
        Integer[] valores={2,9,5,0,3,7,1,4,8,6};

        Stream<Integer> flujo = Arrays.stream(valores);

        System.out.printf("Valores Originales : %s%n", Arrays.asList(valores));

        //ordenacion sobre el flujo
        List<Integer> collect = flujo.sorted().collect(Collectors.toList());
        System.out.printf("Valores ordemadps : %s%n", collect);

        // flujo.forEach(System.out::println);  // si ejecuto esta linea da error ya que el flujo ya ha sido consumido

        List<Integer> lista2 = Arrays.stream(valores).filter(x -> x > 4).
                sorted().
                collect(Collectors.toList());
        System.out.printf( " Valores fltrados y ordenados : %s%n", lista2);
    }
}
