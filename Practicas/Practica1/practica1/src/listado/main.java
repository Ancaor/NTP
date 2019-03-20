package listado;

import java.util.List;
import java.util.Map;

import static listado.Sector.SECTOR1;

public class main {

    public static void main(String args[]) throws Exception {

        ListadoEmpleados a = new ListadoEmpleados("./data/datos.txt");

        System.out.println(a.hayDnisRepetidosArchivo());

        System.out.println(a.contarEmpleadosDnisRepetidos());

        Map<String, List<Empleado>> stringListMap = a.obtenerDnisRepetidosArchivo();

        a.repararDnisRepetidos(stringListMap);

        System.out.println("Hay correos repetidos ? :  " + a.hayCorreosRepetidosArchivo());

        System.out.println(a.contarCorreosRepetidos());

        Map<String, List<Empleado>> stringListMap2 = a.obtenerCorreosRepetidosArchivo();

        a.repararCorreosRepetidos(stringListMap2);

        a.validarListaArchivo();

        System.out.println(a.cargarArchivoAsignacionSector("./data/asignacionSECTOR1.txt"));
        System.out.println(a.cargarArchivoAsignacionSector("./data/asignacionSECTOR2.txt"));
        System.out.println(a.cargarArchivoAsignacionRuta("./data/asignacionRUTA1.txt"));
        System.out.println(a.cargarArchivoAsignacionRuta("./data/asignacionRUTA2.txt"));
        System.out.println(a.cargarArchivoAsignacionRuta("./data/asignacionRUTA3.txt"));

        System.out.println(a.obtenerContadoresRuta(SECTOR1));

        System.out.println(a.obtenerContadoresSectorRuta());
        System.out.println(a.buscarEmpleadosSinSectorSinRuta());
    }
}
