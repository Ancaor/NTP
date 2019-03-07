package base;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.*;

public class ProcesadoArchivoTexto {

    public static void main(String args[]) throws Exception{
        //definir patron que pemita trocear por espacios en blanco
        Pattern patron = Pattern.compile("\\s+");

        //leer todas las lineas del archivo
        Stream<String> lineas = Files.lines(Paths.get("./data/alicia.txt"),StandardCharsets.ISO_8859_1);

        //eliminamos signos de puntuacion
        Stream<String> lineasProcesadas = lineas.map(linea -> linea.replaceAll("(?!')\\p{Punct}", ""));

        //trocear las lineas para tratar con palabras

        Stream<String> palabras = lineasProcesadas.flatMap(linea -> patron.splitAsStream(linea));

        //filtrar palabras vacias

        Stream<String> sinVacias = palabras.filter(palabra -> !palabra.isEmpty());

        // generar un diccionario con entradas tipo <palabra, numero de ocurrencias>
        // queremos que el diccionario sea tipo TreeMap

        TreeMap<String,Long> mapa = sinVacias.collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new,Collectors.counting()));


        //Ahora todo compactado

        TreeMap<String,Long> mapa2 = Files.lines(Paths.get("./data/alicia.txt"),StandardCharsets.ISO_8859_1).
                map(linea -> linea.replaceAll("(?!')\\p{Punct}", "")).
                flatMap(linea -> patron.splitAsStream(linea)).
                filter(palabra -> !palabra.isEmpty()).
                collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new,Collectors.counting()));


        mapa2.forEach(
                (palabra, numero) -> System.out.println(palabra + " : " + numero)
        );

        //Con esto pedo crear un stream
        //mapa2.entrySet().stream().forEach();


        //agrupamiento por inicales, todas las palabras con dicha inicial y contadores

        TreeMap<Character, List<Map.Entry<String,Long>>> mapa3 =  mapa2.
                entrySet().
                stream().
                collect(Collectors.groupingBy(entrada -> entrada.getKey().charAt(0),TreeMap::new,Collectors.toList()));

        mapa3.forEach((inicial,listaPalabras) -> {
            System.out.printf("%n%c%n", inicial);
            listaPalabras.stream().forEach( entrada -> {
                System.out.printf("%13s: %d%n", entrada.getKey(), entrada.getValue());
            });
        });




    }
}
