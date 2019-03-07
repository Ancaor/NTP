package PracticaEmpleados;

public class main {

    public static void main(String args[]) throws Exception {

    ListadoEmpleado a = new ListadoEmpleado("./data/datos.txt");

    System.out.println(a.hayDnisRepetidosArchivo());

    System.out.println(a.contarEmpleadosDnisRepetidos());


    }
}
