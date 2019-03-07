package PracticaEmpleados;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoEmpleado {


    private List<Empleado> listadoArchivo;


    private Map<String,Empleado> listado;


    public ListadoEmpleado(String path) throws Exception{

        Pattern patron = Pattern.compile("\\,+");


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
        boolean repetidos = false;

        long aux = listadoArchivo.stream().map(empleado -> empleado.obtenerDni()).distinct().count();

        //System.out.println("DNI no repetidos : " + aux );

        if(aux < obtenerNumeroEmpleadosArchivo())
            repetidos = true;

        return repetidos;
    }

    public Map<String, List<Empleado>> obtenerDnisRepetidosArchivo(){

        Map<String,List<Empleado>> dnisRepetidos = new TreeMap<>();

        Map<String, List<Empleado>> agrupamientoDni = listadoArchivo.stream().collect(Collectors.groupingBy(Empleado::obtenerDni));

        System.out.println(agrupamientoDni.size());
        List<String> dnisrepetidos = new ArrayList<>();

        agrupamientoDni.forEach((dni, empleados) -> {
            if(empleados.size() > 1) dnisRepetidos.put(dni,empleados);
        });

        return dnisRepetidos;
    }

    public int contarEmpleadosDnisRepetidos(){

        Map<String, List<Empleado>> dnisRepetidos = obtenerDnisRepetidosArchivo();

        return (int) dnisRepetidos.values().stream().flatMap(Collection::stream).count();
    }


}