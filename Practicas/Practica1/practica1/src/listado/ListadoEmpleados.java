package listado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public long cargarArchivoAsignacionSector(String path) throws IOException {

        List<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1).collect(Collectors.toList());

        String sector_s = lineas.get(0);
        final Sector sector = procesarSector(sector_s);

        long errores = lineas.stream().skip(2).
                map(linea -> procesarAsignacionSector(sector,linea)).
                filter(flag -> flag == false).count();

        return errores;

      //  listado.values().stream().forEach(e -> System.out.println(e.generarLineaDniSector()));

    }

    public boolean procesarAsignacionSector(Sector sector, String linea){

        if(listado.containsKey(linea)){
            listado.get(linea).asignarSector(sector);

            return true;
        }else
            return false;
    }

    public boolean procesarAsignacionRuta(Ruta ruta, String linea){

        if(listado.containsKey(linea)){
            listado.get(linea).asignarRuta(ruta);

            return true;
        }else
            return false;
    }

    public long cargarArchivoAsignacionRuta(String path) throws IOException {

        List<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1).collect(Collectors.toList());

        String ruta_s = lineas.get(0);
        Ruta ruta = procesarRuta(ruta_s);

        long errores = lineas.stream().skip(2).
                map(linea -> procesarAsignacionRuta(ruta,linea)).
                filter(flag -> flag == false).count();

        return errores;
    }

    private Sector procesarSector(String cadenaSector){
        Predicate<Sector> condicion = sector -> cadenaSector.equals(sector.name());
        return Arrays.stream(Sector.values()).filter(condicion).findFirst().get();
    }

    private Ruta procesarRuta(String cadenaRuta){
        Predicate<Ruta> condicion = ruta -> cadenaRuta.equals(ruta.name());
        return Arrays.stream(Ruta.values()).filter(condicion).findFirst().get();
    }


    public List <Empleado> buscarEmpleadosSinRuta(Sector sector) {

        return listado.values().stream().filter(empleado -> empleado.obtenerSector() == sector && empleado.obtenerRuta() == Ruta.NORUTA).collect(Collectors.toList());

    }

    public List <Empleado> buscarEmpleadosConSectorSinRuta(){

        return Arrays.stream(Sector.values()).
                filter(sector -> sector != Sector.NOSECTOR).
                map(this::buscarEmpleadosSinRuta).
                flatMap(Collection::stream).
                collect(Collectors.toList());

    }

    public List<Empleado> buscarEmpleadosSinSector(Ruta ruta){
        return listado.values().stream().filter(empleado -> empleado.obtenerSector() == Sector.NOSECTOR && empleado.obtenerRuta() == ruta).collect(Collectors.toList());
    }

    public List <Empleado> buscarEmpleadosSinSectorConRuta(){

        return Arrays.stream(Ruta.values()).
                filter(ruta -> ruta != Ruta.NORUTA).
                map(this::buscarEmpleadosSinSector).
                flatMap(Collection::stream).
                collect(Collectors.toList());

    }

    public Map<Ruta, Long> obtenerContadoresRuta(Sector sector) {

        Map<Ruta, Long> collect = listado.values().stream().filter(empleado -> empleado.obtenerSector() == sector).
                sorted(Comparator.comparing(Empleado::obtenerRuta)).
                collect(Collectors.groupingBy (Empleado::obtenerRuta,
                LinkedHashMap::new,
                Collectors.mapping(Empleado::obtenerRuta, Collectors.counting())));

        return collect;
    }

    public Map<Sector, Map<Ruta, Long>> obtenerContadoresSectorRuta() {

        Map<Sector, Map<Ruta, Long>> collect = Arrays.stream(Sector.values()).
                collect(Collectors.toMap(sector -> sector, this::obtenerContadoresRuta));

        return collect;
    }

    List <Empleado> buscarEmpleadosSinSectorSinRuta(){
        Predicate<Empleado> condicion = (empleado) -> (empleado.obtenerSector() == Sector.NOSECTOR) && (empleado.obtenerRuta() == Ruta.NORUTA) ;

        return listado.values().stream().filter(condicion).collect(Collectors.toList());

    }



}