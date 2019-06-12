import org.scalatest.FunSuite

import Conjunto._

class TestsConjunto extends FunSuite{

  val c1 = conjuntoUnElemento(1)
  val c2 = conjuntoUnElemento(2)

  // Test de método apply

  test("test: C1 contiene 1 y C2 contiene 2") {
    assert(c1(1) && c2(2), "Error: c1 no contiene 1 o c2 no contiene 2")
  }

  // Test de union

  test("test: c1 union c2 == {1,2}"){
    val _union = union(c1,c2)

    assert(_union(1), "Error: Union de c1 y c2 no contiene 1")
    assert(_union(2), "Error: Union de c1 y c2 no contiene 2")
  }

  //test de union general
  test("Test de union (general)"){
    val c3 = Conjunto((x:Int) => x>3)
    val c4 = Conjunto((x:Int) => x>6)

    val _union2 = union(c3,c4)

    assert(_union2(4), "Error: 4 debe estar en la union de c3 y c4")
    assert(_union2(7), "Error: 7 debe estar en la union de c3 y c4")

  }


  //test de interseccion

  test("Test de interseccion"){
    val c5 = Conjunto((x:Int) => x>2)
    val c6 = Conjunto((x:Int) => x < 4)

    val _interseccion = interseccion(c5,c6)

    assert(_interseccion(3), "Error: 3 es el unico elemento de _interseccion")
    assert(!_interseccion(4), "Error: 4 no debe estar en _interseccion")

  }


  //test de diferencia

  test("Test de diferencia"){
    val c7 = Conjunto((x:Int) => x >0)
    val c8 = Conjunto((x:Int) => x< 4)

    val _diferencia = diferencia(c7,c8)

    assert(_diferencia(4),"Error: el conjunto _diferencia debe contener elementos positivos mayores que 3")
    assert(!_diferencia(3),"Error: el conjunto _diferencia debe contener elementos positivos mayores que 3")

  }

  //test de filtrado

  test("Test de filtrado"){
    val c9 = Conjunto((x:Int) => x > 0)
    val predicado1 = (x:Int) => x%2==0

    val _filtrado = filtrar(c9,predicado1) // elementos mayores que 0 pares

    assert(!_filtrado(1))
    assert(_filtrado(2))
    assert(!_filtrado(3))
    assert(_filtrado(4))
  }




  //test de paraTodo

  test("test de paraTodo"){
    val predicado2 = (x:Int) => ((x>0) && (x%2==0))
    val c10 = Conjunto(predicado2)

    assert(paraTodo(c10,predicado2))

  }



  //test de existe

  test("test de existe"){
    val c11 = Conjunto((x:Int) => x >0)
    val predicado3 = (x:Int) => x == 3

    assert(existe(c11,predicado3))


  }

  //test de map
  test("test de map"){
    val c12 = Conjunto((x:Int) => x<6 && x>0) //1,2,3,4,5

    val _map = map(c12,x => x*2) //Cada elemento es el doble de c12 : 2,4,6,8,10

    assert(_map(2))
    assert(_map(8))
    assert(!_map(1))



  }



}
