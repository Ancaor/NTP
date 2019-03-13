package PracticaEmpleados;

import java.util.List;
import java.util.Map;

public class main {

    public static void main(String args[]) throws Exception {

        ListaEmpleados a = new ListaEmpleados("./data/datos.txt");

        System.out.println(a.hayDnisRepetidosArchivo());

        System.out.println(a.contarEmpleadosDnisRepetidos());

        Map<String, List<Empleado>> stringListMap = a.obtenerDnisRepetidosArchivo();

        a.repararDnisRepetidos(stringListMap);

        System.out.println("Hay correos repetidos ? :  " + a.hayCorreosRepetidosArchivo());

        System.out.println(a.contarCorreosRepetidos());

        Map<String, List<Empleado>> stringListMap2 = a.obtenerCorreosRepetidosArchivo();

        a.repararCorreosRepetidos(stringListMap2);
    }
}
