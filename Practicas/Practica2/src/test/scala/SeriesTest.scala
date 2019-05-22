
import org.scalatest.FunSuite
import Funciones.serie
import Funciones.FibonacciLucas
import Funciones.Jacobsthal
import Funciones.PellLucas


class SeriesTest extends FunSuite {

  val numElem = 10

  test("Test de Fibonacci"){

    val coleccionOriginal = Array(0,1,1,2,3,5,8,13,21,34)
    var coleccion = new Array[Int](numElem)

    for(x <- 0 to numElem-1) coleccion(x) = serie(FibonacciLucas)(0,1,x)

    for(x <- 0 to numElem-1) assert(coleccion(x) == coleccionOriginal(x))
  }

  test("Test de Lucas"){

    val coleccionOriginal = Array(2,1,3,4,7,11,18,29,47,76)
    var coleccion = new Array[Int](numElem)

    for(x <- 0 to numElem-1) coleccion(x) = serie(FibonacciLucas)(2,1,x)

    for(x <- 0 to numElem-1) assert(coleccion(x) == coleccionOriginal(x))
  }

  test("Test de Pell"){

    val coleccionOriginal = Array(2,6,14,34,82,198,478,1154,2786,6726)
    var coleccion = new Array[Int](numElem)

    for(x <- 0 to numElem-1) coleccion(x) = serie(PellLucas)(2,6,x)

    for(x <- 0 to numElem-1) assert(coleccion(x) == coleccionOriginal(x))
  }

  test("Test de Pell-Lucas"){

    val coleccionOriginal = Array(2,2,6,14,34,82,198,478,1154,2786)
    var coleccion = new Array[Int](numElem)

    for(x <- 0 to numElem-1) coleccion(x) = serie(PellLucas)(2,2,x)

    for(x <- 0 to numElem-1) assert(coleccion(x) == coleccionOriginal(x))
  }

  test("Test de Jacobsthal"){

    val coleccionOriginal = Array(0,1,1,3,5,11,21,43,85,171)
    var coleccion = new Array[Int](numElem)

    for(x <- 0 to numElem-1) coleccion(x) = serie(Jacobsthal)(0,1,x)

    for(x <- 0 to numElem-1) assert(coleccion(x) == coleccionOriginal(x))
  }
}
