package listado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoEmpleados {


    private List<Empleado> listadoArchivo;


    private Map<String,Empleado> listado;

    /**
     * Constructor de ListaEmpleados
     * @param path ruta del archivo de datos.txt
     * @throws Exception si datos.txt no está en la ruta indicada.
     */

    public ListadoEmpleados(String path) throws Exception{

        listado = new TreeMap<>();

        Stream<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1);

        listadoArchivo = lineas.map( linea -> new Empleado(linea)).collect(Collectors.toList());

    }

    /**
     * Metodo para obtener el numero de empleados recogidos del archivo datos.txt
     * @return numero de empleados recogidos de datos.txt
     */

    public int obtenerNumeroEmpleadosArchivo(){
        return listadoArchivo.size();
    }


    /**
     * Metodo que comprueba si hay empleados con dni igual
     * @return true si hay DNIs repetidos y false en caso contrario
     */
    public boolean hayDnisRepetidosArchivo(){

        long empleadosDNIdistinto = listadoArchivo.stream().map(empleado -> empleado.obtenerDni()).distinct().count();

        return (empleadosDNIdistinto < obtenerNumeroEmpleadosArchivo());
    }

    /**
     * Obtiene los dnis repetidos y sus empleados asociados
     * @return map de dnis y lista de empleados con dicho dni
     */
    public Map<String, List<Empleado>> obtenerDnisRepetidosArchivo(){

        // Se agrupan todos los empleados por su DNI

        Map<String, List<Empleado>> agrupamientoDni = listadoArchivo.stream().
                collect(Collectors.groupingBy(Empleado::obtenerDni));

        //Se fitra agrupamientoDni para quedarnos solo con los dni con mas de un empleado asociado

        Map<String, List<Empleado>> dnisRepetidos = agrupamientoDni.entrySet().stream().
                filter(entrada -> entrada.getValue().size() > 1).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return dnisRepetidos;
    }

    /**
     * Cuenta el numero de empleados que tienen dni repetido
     * @return numero de empleados con dni repetido
     */
    public int contarEmpleadosDnisRepetidos(){

        // Obtengo los dnis repetidos con sus empleados asociados
        Map<String, List<Empleado>> dnisRepetidos = obtenerDnisRepetidosArchivo();

        // Convierto todos las listas de empleados en una sola y cuento cuantos elementos tiene
        return (int) dnisRepetidos.values().stream().flatMap(Collection::stream).count();
    }

    /**
     * Metodo para asignar un dni aleatorio a los empleados con dni repetido
     * @param listaRepetidos dnis que tienen un conjunto de empleados asociados
     */
    public void repararDnisRepetidos(Map<String, List<Empleado>> listaRepetidos){

        //Para cada empleado con dni repetido se busca en listadoArchivo y se le asigna un dni aleatorio

        listaRepetidos.values().stream().flatMap(Collection::stream).
                forEach(entrada -> listadoArchivo.get(listadoArchivo.indexOf(entrada)).asignarDniAleatorio());

        //System.out.println("¿ Quedan dnis repetidos ? : " + hayDnisRepetidosArchivo());

    }

    /**
     * Metodo que comprueba si hay empleados con correo igual
     * @return true si hay Correos repetidos y false en caso contrario
     */
    public boolean hayCorreosRepetidosArchivo(){

        long empleadosCorreoDistinto = listadoArchivo.stream().map(empleado -> empleado.obtenerCorreo()).distinct().count();

        return (empleadosCorreoDistinto < obtenerNumeroEmpleadosArchivo());

    }

    /**
     * Obtiene los correos repetidos y sus empleados asociados
     * @return map de correo y lista de empleados con dicho correo
     */
    public Map<String, List<Empleado>> obtenerCorreosRepetidosArchivo(){

        //Agrupa los empleados por su correo asociado
        Map<String, List<Empleado>> agrupamientoCorreo = listadoArchivo.stream().
                collect(Collectors.groupingBy(Empleado::obtenerCorreo));

        //Filtra el contenido de agrupamientoCorreo para quedarse solo con aquellos correos con mas de un empleado asociado
        Map<String, List<Empleado>> correosRepetidos = agrupamientoCorreo.entrySet().stream().
                filter(entrada -> entrada.getValue().size() > 1).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return correosRepetidos;
    }

    /**
     * Cuenta el numero de correos repetidos
     * @return numero de correos repetidos
     */
    public int contarCorreosRepetidos(){

        // Obtiene los correos con mas de un empleado asociado
        Map<String, List<Empleado>> correosRepetidos = obtenerCorreosRepetidosArchivo();

        // Obtiene una lista unica con todos los empleados con correo repetido y los cuenta
        return (int) correosRepetidos.values().stream().flatMap(Collection::stream).count();
    }

    /**
     * Metodo para asignar un correo aleatorio a los empleados con correo repetido
     * @param listaRepeticiones correos que tienen un conjunto de empleados asociados
     */
    public void repararCorreosRepetidos(Map<String, List<Empleado>> listaRepeticiones){

        //Para cada empleado con correo repetido se busca en listadoArchivo y se le asigna un correo

        listaRepeticiones.values().stream().flatMap(Collection::stream).
                forEach(entrada -> listadoArchivo.get(listadoArchivo.indexOf(entrada)).generarCorreoCompleto());


       // System.out.println("¿ Quedan correos repetidos ? : " + hayCorreosRepetidosArchivo());

    }

    /**
     * Genera el listado con el contenido de listadoArchivo que anteriormente ya se ha sanitizado
     */
    public void validarListaArchivo(){

        listado = listadoArchivo.stream().collect(Collectors.toMap(Empleado::obtenerDni, empleado -> empleado, (e1, e2) -> e1));

    }

    /**
     * Metodo que carga un archivo de asignacion de sectores y actualiza el listado asignando sector a los dnis indicados en el archivo de asignación
     * @param path ruta del archivo de asignacion de sectores
     * @return numero de errores en la asignación de sector
     * @throws IOException
     */
    public long cargarArchivoAsignacionSector(String path) throws IOException {

        List<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1).collect(Collectors.toList());

        String sector_s = lineas.get(0);
        Sector sector = procesarSector(sector_s);

        long errores = lineas.stream().skip(2).
                map(linea -> procesarAsignacionSector(sector,linea)).
                filter(flag -> flag == false).count();

        return errores;

    }

    /**
     * Asigna un sector a un empleado por su dni
     * @param sector sector al que se va a asignar al empleado
     * @param linea linea que contiene el dni del empleado al que se le asignara el sector
     * @return true si la asignacion se procesa adecuadamente y false si no se encuentra el empleado con el dni indicado
     */
    public boolean procesarAsignacionSector(Sector sector, String linea){

        if(listado.containsKey(linea)){
            listado.get(linea).asignarSector(sector);

            return true;
        }else
            return false;
    }

    /**
     * Asigna una ruta a un empleado por su dni
     * @param ruta ruta a la que se va a asignar al empleado
     * @param linea linea que contiene el dni del empleado al que se le asignara la ruta
     * @return true si la asignacion se procesa adecuadamente y false si no se encuentra el empleado con el dni indicado
     */
    public boolean procesarAsignacionRuta(Ruta ruta, String linea){

        if(listado.containsKey(linea)){
            listado.get(linea).asignarRuta(ruta);

            return true;
        }else
            return false;
    }

    /**
     * Metodo que carga un archivo de asignacion de rutas y actualiza el listado asignando ruta a los dnis indicados en el archivo de asignación
     * @param path ruta del archivo de asignacion de rutas
     * @return numero de errores en la asignación de ruta
     * @throws IOException
     */
    public long cargarArchivoAsignacionRuta(String path) throws IOException {

        List<String> lineas = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1).collect(Collectors.toList());

        String ruta_s = lineas.get(0);
        Ruta ruta = procesarRuta(ruta_s);

        long errores = lineas.stream().skip(2).
                map(linea -> procesarAsignacionRuta(ruta,linea)).
                filter(flag -> flag == false).count();

        return errores;
    }

    /**
     * Metodo auxiliar para obtener el enum de Sector dado un string
     * @param cadenaSector cadena que contiene el sector que se desea
     * @return objeto Sector que cohincide en nombre con cadenaSector
     */
    private Sector procesarSector(String cadenaSector){
        Predicate<Sector> condicion = sector -> cadenaSector.equals(sector.name());
        return Arrays.stream(Sector.values()).filter(condicion).findFirst().get();
    }

    /**
     * Metodo auxiliar para obtener el enum de Ruta dado un string
     * @param cadenaRuta cadena que contiene la ruta que se desea
     * @return objeto Ruta que cohincide en nombre con cadenaRuta
     */
    private Ruta procesarRuta(String cadenaRuta){
        Predicate<Ruta> condicion = ruta -> cadenaRuta.equals(ruta.name());
        return Arrays.stream(Ruta.values()).filter(condicion).findFirst().get();
    }

    /**
     * Busca empeados sin ruta asignada y que pertenecen a un sector concreto
     * @param sector sector al que pertenecen los empleados sin ruta
     * @return lista de empleados sin ruta asignada pertenecientes a un sector indicado
     */
    public List <Empleado> buscarEmpleadosSinRuta(Sector sector) {

        // filtra del listado los empleados pertenecientes a un sector y que ademas no tienen ruta asignada
        return listado.values().stream().filter(empleado -> empleado.obtenerSector() == sector && empleado.obtenerRuta() == Ruta.NORUTA).
                collect(Collectors.toList());

    }

    /**
     * Obtiene una lista de empleados sin sector y sin ruta asignada
     * @return Lista de empleados
     */
    public List <Empleado> buscarEmpleadosConSectorSinRuta(){

        return Arrays.stream(Sector.values()).
                filter(sector -> sector != Sector.NOSECTOR).
                map(this::buscarEmpleadosSinRuta).
                flatMap(Collection::stream).
                collect(Collectors.toList());

    }

    /**
     * Obtiene una lista de empleados sin sector pertenecientes a una ruta
     * @param ruta ruta a la que pertenecen los empleados
     * @return lista de empleados
     */
    public List<Empleado> buscarEmpleadosSinSector(Ruta ruta){
        return listado.values().stream().filter(empleado -> empleado.obtenerSector() == Sector.NOSECTOR && empleado.obtenerRuta() == ruta).collect(Collectors.toList());
    }


    /**
     * Obtiene una lista de empleados sin sector con una ruta asignada
     * @return lista de empleados
     */
    public List <Empleado> buscarEmpleadosSinSectorConRuta(){

        return Arrays.stream(Ruta.values()).
                filter(ruta -> ruta != Ruta.NORUTA).
                map(this::buscarEmpleadosSinSector).
                flatMap(Collection::stream).
                collect(Collectors.toList());

    }

    /**
     * Cuenta el numero de empleados pertenecientes a cada ruta dentro de un sector indicado
     * @param sector sector al que pertenecen todos los empleados
     * @return mapa de rutas con su numero de empleados asociado
     */
    public Map<Ruta, Long> obtenerContadoresRuta(Sector sector) {

        Map<Ruta, Long> collect = listado.values().stream().filter(empleado -> empleado.obtenerSector() == sector).
                sorted(Comparator.comparing(Empleado::obtenerRuta)).
                collect(Collectors.groupingBy (Empleado::obtenerRuta,
                LinkedHashMap::new,
                Collectors.mapping(Empleado::obtenerRuta, Collectors.counting())));

        return collect;
    }

    /**
     * Cuenta el numero de empelados pertenecientes a una ruta y a un sector concretos
     * @return
     */
    public Map<Sector, Map<Ruta, Long>> obtenerContadoresSectorRuta() {

        Map<Sector, Map<Ruta, Long>> collect = Arrays.stream(Sector.values()).
                collect(Collectors.toMap(sector -> sector, this::obtenerContadoresRuta));

        return collect;
    }

    /**
     * Obtiene la lista de empleados sin ruta ni sector asignados
     * @return
     */
    List <Empleado> buscarEmpleadosSinSectorSinRuta(){
        Predicate<Empleado> condicion = (empleado) -> (empleado.obtenerSector() == Sector.NOSECTOR) && (empleado.obtenerRuta() == Ruta.NORUTA) ;

        return listado.values().stream().filter(condicion).collect(Collectors.toList());

    }



}