import scala.annotation.tailrec
import Math.sqrt,Math.floor

object Funciones {

  //Ejercicio 1. Triangulo de Pascal

  /**
    * Función para calcular triangulo de pascal
    * @param fila fila del elemento a calcular
    * @param columna columna del elemento a calcular
    * @return valor del elemento
    */
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

  /**
    * Funcion genérica para objetener elementos de series
    * @param funcion serie que se esata utilizando
    * @param t0 elemento 0 de la serie
    * @param t1 elemento 1 de la serie
    * @param t termino a calcular
    * @return valor del término a calcular
    */
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

  /**
    * Funcion que calcula si los parentesis están balanceados en una cadena
    * @param cadena cadena a analizar
    * @return true si esta balanceada, false si no lo esta
    */
  def chequearBalance(cadena: List[Char]): Boolean = {

    /**
      * Funcion auxiliar recursiva que añade 1 al acumulador si encuentra un "(" y quita 1
      * si encuentra un ")", de modo que si la cadena termina y acumulador es 0 la cadena esta balanceada
      * @param cadena
      * @param acum
      * @return
      */
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

  /**
    * Funcion que dadas unas monedas y una cantidad devuelve todos los posibles cambios
    * @param cantidad cantidad que se desea cambiar
    * @param monedas tipos de monedas disponibles para el cambio
    * @return lista de posibles cambios
    */
  def listarCambiosPosibles(cantidad:Int,monedas:List[Int]) : List[List[Int]] = {

    /**
      * Funcion recursiva que va calculando la lista de posibles cambios
      * @param cantidad cantidad que se desea cambiar
      * @param monedas tipo de monedas disponibles
      * @param acum lista de soluciones ( cambios )
      * @param listaLocal lista que contiene una solucion
      * @return lista con todas las soluciones
      */

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

    // ordeno las monedas de forma decreciente y llamo a loop
    loop(cantidad, monedas.sorted(Ordering.Int.reverse),acum,List())
  }

  //Ejercicio 5. Busqueda binaria generica

  /**
    * Funcion de busqueda binaria
    * @param coleccion vector ordenado en el que se va a buscar
    * @param aBuscar elemto que se busca
    * @param criterio criterio de ordenacion de la coleccion
    * @tparam A
    * @return posicion del elemento si lo ha encontrado, si no devuelve -1
    */
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



  //Ejercicio 6. Busqueda a Saltos generica recursiva

  /**
    * Funcion de busqueda a saltos
    * @param coleccion vector ordenado en el que se va a buscar
    * @param aBuscar elemento a buscar
    * @param criterio criterio de ordeacion del vector
    * @tparam A
    * @return
    */
  def jumpSearch[A](coleccion : Array[A], aBuscar: A, criterio : (A,A) => Boolean) : Int = {

    val blockSize = Math.floor(sqrt(coleccion.size)).toInt // tamaño de bloque
    val coleccionSize = coleccion.length

    /**
      * Funcion recursiva que busca el bloque en el que debe estar el elemento aBuscar
      * @param posInicial posicion inicial del vector
      * @param BlockSize tamaño de bloque
      * @return
      */
    @tailrec
    def goBloque(posInicial: Int, BlockSize: Int): Int = {

      val step = posInicial + blockSize

      if (criterio(coleccion(Math.min(step, coleccionSize) - 1), aBuscar)) {

        val newPosInicial = step
        if (step >= coleccionSize) -1
        else {
          goBloque(newPosInicial, blockSize)
        }

      } else {
        goLineal(posInicial)
      }

    }

    /**
      * Funcion que realiza la busqueda lineal una vez se conoce el bloque en el que debe estar
      * el elemento aBuscar
      * @param posInicial posicion inicial del bloque
      * @return
      */
    def goLineal(posInicial: Int): Int = {

      var prev = posInicial
      val step = posInicial + blockSize
      while(criterio(coleccion(prev),aBuscar)){
        prev += 1
        if(prev == Math.min(step,coleccionSize)) -1
      }

      if(coleccion(prev) == aBuscar) prev
      else -1

    }

    goBloque(0, blockSize)

  }



  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

       // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
     // Se muestran 10 y 10 filas
     for (col <- 0 to row)
      print(calcularValorTrianguloPascal(row, col) + " ")

     // Salto de linea final para mejorar la presentacion
     println()
     }

     // Se muestra el valor que debe ocupar la columna 5 en la fila 10
     println( "Valor de triangulo de pascal, fila:14, columna:1 = " + calcularValorTrianguloPascal(14, 1) )

    println()

    //*****************************************

    println("................... Series ...................")


    println("Serie Fibonacci para t=5: ")
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

    println("................... Cambio de monedas ...................")
    println()
    val monedas = List(2,5,1)
    val cantidad = 10

    println("monedas : " +monedas + ", cantidad : " + cantidad)
    println()

    val cambios = listarCambiosPosibles(cantidad,monedas)

    for (lista <- cambios) {
      println( lista.toString() + " = " + lista.reduce(_+_))
    }

    println()

    println("................... Busqueda Binaria ...................")
    println()
    println("Busqueda binaria del elemento 55 en la serie Fibonacci")
    val coleccion = Array(0,1,1,2,3,5,9,13,21,34,55,89,145,237,377,610)
    val aBuscar = serie(FibonacciLucas)(0,1,10)

    println(coleccion(busquedaBinaria(coleccion,55,(a:Int,b:Int)=> a < b)))
    println(aBuscar == coleccion(busquedaBinaria(coleccion,55,(a:Int,b:Int)=> a < b)))

    println("................... Busqueda a saltos recursiva...................")
    println()
    println("Busqueda de salto del elemento 55 en la serie Fibonacci")
    println(coleccion(jumpSearch(coleccion,55,(a:Int,b:Int)=> a < b)))
    println(aBuscar == coleccion(jumpSearch(coleccion,55,(a:Int,b:Int)=> a < b)))


  }







}
