package PracticaEmpleados;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
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

        System.out.println(listadoArchivo.get(0).toString());


    }

    public int obtenerNumeroEmpleadosArchivo(){
        return listadoArchivo.size();
    }

    public boolean hayDnisRepetidosArchivo(){
return true;
    }


}