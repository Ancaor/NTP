import org.scalatest.FunSuite

import Funciones.listarCambiosPosibles

class CambioMonedasTest extends FunSuite {

  val monedas1 = List(2, 5, 1)
  val cantidad1 = 10
  val monedas2 = List(2, 1)
  val cantidad2 = 4


  val cambios1 = listarCambiosPosibles(cantidad1, monedas1)
  val cambios2 = listarCambiosPosibles(cantidad2, monedas2)

  test("Cambios para cantidad = 10 y monedas = [2,5,1]") {
    println("Cambios para cantidad = 10 y monedas = [2,5,1]")
    for (lista <- cambios1) {
      println( lista.toString() + " = " + lista.reduce(_+_))
      assert(lista.reduce(_ + _) == cantidad1)
    }
  }
  test("Cambios para cantidad = 4 y monedas = [2,1]") {
    println("Cambios para cantidad = 4 y monedas = [2,1]")
    for (lista <- cambios2) {
      println( lista.toString() + " = " + lista.reduce(_+_))
      assert(lista.reduce(_ + _) == cantidad2)
    }
  }

}
