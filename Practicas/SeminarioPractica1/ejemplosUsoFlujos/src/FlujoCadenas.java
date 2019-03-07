import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlujoCadenas {
    public static void main(String args[]){
        String [] cadenas = {"Rojo","Naranja", "Amarillo", "Verde", "azul", "indigo", "Violeta"};


        //creacion de flujo
        List<String> list1 = Arrays.stream(cadenas).
                map(String::toUpperCase).
                collect(Collectors.toList());

        System.out.printf("Lista todo mayuscula : %s%n", list1);

        //ordenacion con detalles
        List<String> list2 = Arrays.stream(cadenas).
                filter(cadena -> cadena.compareToIgnoreCase("m") > 0).
                sorted(String.CASE_INSENSITIVE_ORDER.reversed()).
                collect(Collectors.toList());

        System.out.printf("Lista filtrada y ordenada : %s%n", list2);




    }
}
