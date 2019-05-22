import Funciones.jumpSearch
import Funciones.serie
import Funciones.FibonacciLucas
import org.scalatest.FunSuite

class BusquedaASaltosTest extends FunSuite {

  val r = scala.util.Random
  var start = 0;
  var end = 10;
  var end2 = 100;

  var coleccion = new Array[Int](end2)
  for(x <- 0 to end2-1) coleccion(x) = x*2


  test("Busqueda de elemento en posicion 54"){
    val criterio = (a:Int,b:Int) => a < b
    val aBuscarIndex = 54
    val aBuscar = coleccion(aBuscarIndex)

    println("Encontrado elemento en posicion 54 ? : " + (coleccion(jumpSearch(coleccion,aBuscar,criterio)) == aBuscar))

    assert(jumpSearch(coleccion,aBuscar,criterio) == aBuscarIndex)
  }

  test("Busqueda de elemento en posicion 99"){
    val criterio = (a:Int,b:Int) => a < b
    val aBuscarIndex = 99
    val aBuscar = coleccion(aBuscarIndex)

    println("Encontrado elemento en posicion 99 ? : " + (coleccion(jumpSearch(coleccion,aBuscar,criterio)) == aBuscar))

    assert(jumpSearch(coleccion,aBuscar,criterio) == aBuscarIndex)
  }


}
