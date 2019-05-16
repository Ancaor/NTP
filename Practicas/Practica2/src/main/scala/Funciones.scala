import scala.annotation.tailrec
import Math.sqrt,Math.floor

object Funciones {

  //Ejercicio 1. Triangulo de Pascal

  def calcularValorTrianguloPascal(fila: Int, columna: Int): Long = {
    @tailrec
    def factorial(numero : Int, acumulador : Long): Long = {
      if (numero < 0) 0
      else if (numero == 0) 1
      else if (numero == 1) acumulador
      else factorial(numero - 1, (numero).toLong * (acumulador))
    }
    factorial(fila,1) / (factorial(columna,1) * factorial(fila-columna,1))
  }

  // Ejercicio 2. Series

  def FibonacciLucas (x1:Int,x2:Int):Int = {
    x1+x2
  }

  def PellLucas (x1:Int,x2:Int):Int = {
    x1+(2*x2)
  }

  def Jacobsthal (x1:Int,x2:Int):Int = {
    (2*x1)+x2
  }

  def serie (funcion: (Int,Int)=> Int)(t0:Int , t1:Int, t:Int) : Int = {
    @tailrec
    def go(ta:Int,tm2:Int, tm1:Int): Int ={
      if(ta == 0) tm2
      else if(ta == 1) tm1
      else go(ta-1,tm1, funcion(tm2,tm1))

    }
    go(t,t0,t1)

  }

  // Ejercicio 3. Balance de parentesis

  def chequearBalance(cadena: List[Char]): Boolean = {

    @annotation.tailrec
    def go(cadena: List[Char], acum: Int = 0): Boolean = {

      if(cadena.isEmpty) acum == 0
      else if(acum < 0) false
      else if(cadena.head == '(') go(cadena.tail,acum+1)
      else if(cadena.head == ')') go(cadena.tail,acum-1)
      else go(cadena.tail,acum)

    }

    go(cadena,0)
  }

  // Ejercicio 4. Contador de posibles cambios de moneda // exige monedas orden descendente ejem[5,2,1]


  def listarCambiosPosibles(cantidad:Int,monedas:List[Int]) : List[List[Int]] = {

    def loop(cantidad: Int, monedas: List[Int],acum:List[List[Int]],listaLocal:List[Int]): List[List[Int]] = {
      if (cantidad < 0 || monedas.isEmpty ){
        acum  // devuelve las soluciones que ya habia
      }
      else if (cantidad == 0 ){
       listaLocal :: acum  // añado la solucion encontrada a la lista de soluciones
      }
      else {

        /*
        * A continuacion se hace la concatenacion de dos llamadas recursivas que devuelven listas,
        *
        * En la primera llamada a loop se descarta el primer tipo de moneda que se tiene y se procede a intentar calcular el cambio con el resto
        *
        * En la segunda llamada se utiliza el primer tipo de moneda y se mantiene en la lista para que se pueda usar posteriormente
        * */

        loop(cantidad, monedas.tail,acum,listaLocal) ::: loop(cantidad - monedas.head, monedas,acum,monedas.head :: listaLocal)


      }
    }

    var acum = List()

    // importante que el orden de las monedas sea decreciente
    loop(cantidad, monedas.sorted(Ordering.Int.reverse),acum,List())
  }

  //Ejercicio 5. Busqueda binaria generica


  def busquedaBinaria[A](coleccion : Array[A], aBuscar: A, criterio : (A,A) => Boolean) : Int = {

    /**
      * Metodo recursivo auxiliar de busquedaBinaria
      * @param coleccion Array en el que se busca el elemento aBuscar
      * @param acum posicion acumulada del Array, originalmente es 0 ya que se comienza a buscar
      *             desde el inicio del Array original, tambien puede verse como la posicion origen del array original
      * @return -1 si no encontrado o la posicion del elemento aBuscar si lo ha encontrado
      */

    @annotation.tailrec
    def go(coleccion: Array[A], acum: Int = 0): Int = {

      val mitad = coleccion.length / 2 // posicion de la mitad del Array
      val valorMitad = coleccion(mitad) // valor de la posicion de la mitad

      if (valorMitad == aBuscar) acum + mitad // Si el valor que buscamos esta en la mitad se devuelve su posicion
      else if (coleccion.length == 1) -1 // Si solo queda un elemento no se ha encontrado
      else {
        if (criterio(valorMitad, aBuscar)) {

          if (mitad + 1 == coleccion.length) -1
          else go(coleccion.slice(mitad + 1, coleccion.length), acum + mitad + 1)

        }
        else {
          if (mitad == 0) -1
          else go(coleccion.slice(0, mitad), acum)
        }
      }
    }

    if(coleccion.length == 0) -1
    else go(coleccion)
  }



  //Ejercicio 6. Busqueda a Saltos generica


  def busquedaASaltos[A](coleccion : Array[A], aBuscar: A, criterio : (A,A) => Boolean) : Int = {

    val blockSize = Math.floor(sqrt(coleccion.size)).toInt // tamaño de bloque
    var step  = blockSize;

    val coleccionSize = coleccion.length

    var prev = 0;

    while( criterio(  coleccion(Math.min(step,coleccionSize)-1),aBuscar)) {

      prev = step
      step += blockSize
      if(prev >= coleccionSize) -1

    }

    while(criterio(coleccion(prev),aBuscar)) {
      prev+=1
      if(prev == Math.min(step,coleccionSize)) -1
    }

    if(coleccion(prev) == aBuscar) prev
    else -1


  }


  def busquedaASaltosRecursivo[A](coleccion : Array[A], aBuscar: A, criterio : (A,A) => Boolean) : Int = {

    //0,1,1,2,3,5,9,13,21,34,55,89,145,237,377,610

    def go(coleccion2 : Array[A], acum: Int) : Int = {
      val blockSize = Math.floor(sqrt(coleccion2.size)).toInt // tamaño de bloque
      println("Acum_old: " + acum)
      println("Coleccion:")
      coleccion2.foreach(println(_))
      println()
      println("Blocksize : " + blockSize)
      println("Coleccion(blocksize - 1 ): " + coleccion2(blockSize-1))

      if(criterio(coleccion2(blockSize-1),aBuscar)){
        val newAcum = acum + blockSize
        println("Acum: " + newAcum)
        val newColeccion = coleccion2.slice(blockSize+1,coleccion2.length)
        println("New Coleccion:")
        newColeccion.foreach(println(_))
        println()

        go(newColeccion,newAcum)

      }else acum+blockSize+1
    }

    go(coleccion,0)


  }










  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

       // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 25) {
     // Se muestran 10 y 10 filas
     for (col <- 0 to row)
      print(calcularValorTrianguloPascal(row, col) + " ")

     // Salto de linea final para mejorar la presentacion
     println()
     }

     // Se muestra el valor que debe ocupar la columna 5 en la fila 10
     println(calcularValorTrianguloPascal(14, 1))

    println()

    //*****************************************

    println("................... Series ...................")


    println("Serie Fibonacci para t=8: ")
    println(serie(FibonacciLucas)(0,1,5))
    println("Serie Lucas para t=6:")
    println(serie(FibonacciLucas)(2,1,6))
    println("Serie Pell para t=6:")
    println(serie(PellLucas)(2,6,6))
    println("Serie Pell-Lucas para t=6:")
    println(serie(PellLucas)(2,2,6))
    println("Serie Jacobsthal para t=6:")
    println(serie(Jacobsthal)(0,1,6))

    println()


    /****************************************************/
    println("................... Parentesis ...................")

    val cadena ="Te lo dije (eso esta (todavia) hecho))"
    println(chequearBalance(cadena.toList))
    println()

    /****************************************************/

    //Cambio monedas

    println("................... Cambio de monedas ...................")

    val monedas = List(2,5,1)
    val cantidad = 10


    val cambios = listarCambiosPosibles(cantidad,monedas)

    for (lista <- cambios) {
      println( lista.toString() + " = " + lista.reduce(_+_))
    }

    println()

    println("................... Busqueda Binaria ...................")
    val coleccion = Array(0,1,1,2,3,5,9,13,21,34,55,89,145,237,377,610)
    val aBuscar = serie(FibonacciLucas)(0,1,10)

    println(coleccion(busquedaBinaria(coleccion,55,(a:Int,b:Int)=> a < b)))
    println(aBuscar == coleccion(busquedaBinaria(coleccion,55,(a:Int,b:Int)=> a < b)))

    println("................... Busqueda a saltos ...................")

    println(coleccion(busquedaASaltos(coleccion,55,(a:Int,b:Int)=> a < b)))
    println(aBuscar == coleccion(busquedaASaltos(coleccion,55,(a:Int,b:Int)=> a < b)))

    println(coleccion(busquedaASaltosRecursivo(coleccion,55,(a:Int,b:Int)=> a < b)))
    //println(aBuscar == coleccion(busquedaASaltosRecursivo(coleccion,55,(a:Int,b:Int)=> a < b)))

  }







}
