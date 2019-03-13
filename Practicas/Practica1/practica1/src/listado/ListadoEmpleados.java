package listado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoEmpleados {


    private List<Empleado> listadoArchivo;


    private Map<String,Empleado> listado;


    public ListadoEmpleados(String path) throws Exception{

        listado = new TreeMap<>();

        Stream<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1);

        listadoArchivo = lineas.map( linea -> new Empleado(linea)).collect(Collectors.toList());

        //Stream<String> palabras = lineas.flatMap(linea -> patron.splitAsStream(linea));

        //palabras.forEach(palabra -> System.out.print(palabra));
       // listadoArchivo.forEach(empleado -> System.out.println(empleado.toString()));


    }

    public int obtenerNumeroEmpleadosArchivo(){
        return listadoArchivo.size();
    }

    public boolean hayDnisRepetidosArchivo(){

        long aux = listadoArchivo.stream().map(empleado -> empleado.obtenerDni()).distinct().count();

        //System.out.println("DNI no repetidos : " + aux );


        return (aux < obtenerNumeroEmpleadosArchivo());
    }

    public Map<String, List<Empleado>> obtenerDnisRepetidosArchivo(){

        Map<String,List<Empleado>> dnisRepetidos = new TreeMap<>();

        Map<String, List<Empleado>> agrupamientoDni = listadoArchivo.stream().collect(Collectors.groupingBy(Empleado::obtenerDni));

        agrupamientoDni.forEach((dni, empleados) -> {
            if(empleados.size() > 1) dnisRepetidos.put(dni,empleados);
        });

        return dnisRepetidos;
    }

    public int contarEmpleadosDnisRepetidos(){

        Map<String, List<Empleado>> dnisRepetidos = obtenerDnisRepetidosArchivo();

        return (int) dnisRepetidos.values().stream().flatMap(Collection::stream).count();
    }

    public void repararDnisRepetidos(Map<String, List<Empleado>> listaRepetidos){

        listadoArchivo.forEach(empleado -> {
            if(listaRepetidos.containsKey(empleado.obtenerDni())){
                empleado.asignarDniAleatorio();
            }
        });

        System.out.println("¿ Quedan dnis repetidos ? : " + hayDnisRepetidosArchivo());

    }

    public boolean hayCorreosRepetidosArchivo(){
        boolean repetidos = false;

        long aux = listadoArchivo.stream().map(empleado -> empleado.obtenerCorreo()).distinct().count();

        //System.out.println("Correos no repetidos : " + aux );

        if(aux < obtenerNumeroEmpleadosArchivo())
            repetidos = true;

        return repetidos;
    }

    public Map<String, List<Empleado>> obtenerCorreosRepetidosArchivo(){
        Map<String,List<Empleado>> correosRepetidos = new TreeMap<>();

        Map<String, List<Empleado>> agrupamientoCorreo = listadoArchivo.stream().collect(Collectors.groupingBy(Empleado::obtenerCorreo));

        agrupamientoCorreo.forEach((correo, empleados) -> {
            if(empleados.size() > 1) correosRepetidos.put(correo,empleados);
        });

        return correosRepetidos;
    }

    public int contarCorreosRepetidos(){

        Map<String, List<Empleado>> correosRepetidos = obtenerCorreosRepetidosArchivo();

        return (int) correosRepetidos.values().stream().flatMap(Collection::stream).count();
    }

    public void repararCorreosRepetidos(Map<String, List<Empleado>> listaRepeticiones){

        listadoArchivo.forEach(empleado -> {
            if(listaRepeticiones.containsKey(empleado.obtenerCorreo())){
                empleado.generarCorreoCompleto();
            }
        });

        System.out.println("¿ Quedan correos repetidos ? : " + hayCorreosRepetidosArchivo());

    }

    public void validarListaArchivo(){

       // Map<String, List<Empleado>> dnisRepetidos = this.obtenerDnisRepetidosArchivo();
       // Map<String, List<Empleado>> correosRepetidos = this.obtenerCorreosRepetidosArchivo();

       // repararDnisRepetidos(dnisRepetidos);
       // repararCorreosRepetidos(correosRepetidos);

        listado = listadoArchivo.stream().collect(Collectors.toMap(Empleado::obtenerDni, empleado -> empleado, (e1, e2) -> e1));

    }

    public void cargarArchivoAsignacionSector(String path) throws IOException {

        Stream<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1);

        String sector = lineas.findFirst().get();
        final Sector sector_ = procesarSector(sector);


        lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1);

        lineas  = lineas.skip(2);


        lineas.forEach( (linea) -> {
            if(listado.containsKey(linea)) listado.get(linea).asignarSector(sector_);
        });

      //  listado.values().stream().forEach(e -> System.out.println(e.generarLineaDniSector()));

    }

    private Sector procesarSector(String cadenaSector){
        Predicate<Sector> condicion = sector -> cadenaSector.equals(sector.name());
        return Arrays.stream(Sector.values()).filter(condicion).findFirst().get();
    }


    public void cargarArchivoAsignacionRuta(String s) {


    }

    public Map<String, Empleado> buscarEmpleadosSinRuta(Sector nosector) {

        //listado.entrySet().stream().filter(entry -> !entry.getValue().obtenerSector().equals(nosector)).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (e1, e2) -> e1)))


        return null;
    }

    public Map<Ruta, Long> obtenerContadoresRuta(Sector sector1) {

        return null;
    }

    public Map<Sector, Map<Ruta, Long>> obtenerContadoresSectorRuta() {
        return null;
    }



}