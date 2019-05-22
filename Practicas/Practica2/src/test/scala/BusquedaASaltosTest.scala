import Funciones.jumpSearch
import org.scalatest.FunSuite

class BusquedaASaltosTest extends FunSuite {

  val r = scala.util.Random
  var start = 0;
  var end = 10;
  var end2 = 100;




  test("Busqueda de un elemento"){
    var criterio = (a:Int,b:Int) => a < b
    var coleccion:Array[Int] = new Array[Int](end+1)
    for(i <- start to end) yield coleccion(i) = r.nextInt(10)
    coleccion = coleccion.sortWith(criterio)

    coleccion.foreach(print(_))

    var aBuscarIndex = 1 + r.nextInt((end-start) +1)
    println("index = " + aBuscarIndex)
    var aBuscar = coleccion(aBuscarIndex)
    println("valor = " + aBuscar)
    println(jumpSearch(coleccion,aBuscar,criterio))
    assert(jumpSearch(coleccion,aBuscar,criterio) == aBuscarIndex)
}


/*
  test("Busqueda de otro elemento elemento"){
    var criterio = (a:Int,b:Int) => a < b
    var coleccion2:Array[Int] = new Array[Int](end2+1)
    for(i <- start to end2) yield coleccion2(i) = r.nextInt(500)
    coleccion2 = coleccion2.sortWith(criterio)

    //coleccion.foreach(print(_))

    var aBuscarIndex2 = 1 + r.nextInt((end2-start) +1)
    println("index = " + aBuscarIndex2)
    var aBuscar2 = coleccion2(aBuscarIndex2)
    println("valor = " + aBuscar2)
    println(jumpSearch(coleccion2,aBuscar2,criterio))
    assert(jumpSearch(coleccion2,aBuscar2,criterio) == aBuscarIndex2)
  }
*/

}
