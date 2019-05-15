import Funciones.busquedaASaltos
import org.scalatest.FunSuite

class BusquedaASaltosTest extends FunSuite {

  val r = scala.util.Random
  var start = 1;
  var end = 20;





  test("Busqueda de un elemento"){
    var criterio = (a:Int,b:Int) => a < b
    var coleccion:Array[Int] = new Array[Int](end+1)
    for(i <- start to end) yield coleccion(i) = r.nextInt(100)
    coleccion = coleccion.sortWith(criterio)

    //coleccion.foreach(print(_))

    var aBuscarIndex = 1 + r.nextInt((end-start) +1)
    println("index = " + aBuscarIndex)
    var aBuscar = coleccion(aBuscarIndex)
    println("valor = " + aBuscar)
    println(busquedaASaltos(coleccion,aBuscar,criterio))
    assert(busquedaASaltos(coleccion,aBuscar,criterio) == aBuscarIndex)
}

  end = 100
  test("Busqueda de otro elemento elemento"){
    var criterio = (a:Int,b:Int) => a < b
    var coleccion:Array[Int] = new Array[Int](end+1)
    for(i <- start to end) yield coleccion(i) = r.nextInt(500)
    coleccion = coleccion.sortWith(criterio)

    //coleccion.foreach(print(_))

    var aBuscarIndex = 1 + r.nextInt((end-start) +1)
    println("index = " + aBuscarIndex)
    var aBuscar = coleccion(aBuscarIndex)
    println("valor = " + aBuscar)
    println(busquedaASaltos(coleccion,aBuscar,criterio))
    assert(busquedaASaltos(coleccion,aBuscar,criterio) == aBuscarIndex)
  }


}
