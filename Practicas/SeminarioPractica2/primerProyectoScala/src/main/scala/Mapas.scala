object Mapas extends App{

  //creacion de conjunto (invariable)
  var ciudades = Set("Granada", "Jaen", "Malaga", "Almeria")

  ciudades += "Sevilla"

  println(ciudades)

  import scala.collection.mutable.Set

  val asignaturas = Set("Matematicas", "Lengua" , "Dibujo")
  asignaturas += "Fisica"

  println(asignaturas)

  import scala.collection.immutable.HashSet

  val colores = HashSet("rojo", "amarillo" , "verde")

  val dias = Map(1 -> "lunes", 2 -> "martes", 3 -> "miercoles")
  println(dias)

  //tamaño del mapa

  println("tam de mapa : " + dias.size)

  val masDias = dias + (4 -> "jueves")
  println(masDias)


  //contiene clave ?

  val contiene: Boolean = masDias.contains(3)
  println(contiene)

  //acceso a todas las claves

  val claves: Predef.Set[Int] = dias.keySet
  val valores: Iterable[String] = dias.values
  println("primer valor: " + valores.head)
  println( "ultimo valor: " + valores.last)

  //formas de acceso a entradas

  val primerDia: String = dias.get(1).getOrElse("nulo")
  val segundoDia = dias(2)


  //mapa heterogeneo

  val mapaHeterogeneo: Map[Any, Any] =Map(1->"lunes", 2->"martes", "jueves" -> 4)

  //mapas con valores por defecto

  val diasDefecto = Map(1 -> "lunes", 2 -> "martes") withDefaultValue("fiesta")

  println(diasDefecto(5))

  //forma de quitar entradas
  val aux = dias - (1) // recordar que dias es inmutable
  dias - (1,2)
  dias -- List(1,2)


  //
}
