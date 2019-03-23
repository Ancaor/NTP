object MasListas extends App{ //App nos implementa el main

  val lista1=List(1,2,3)
  val lista2 = List(1,2,3)

  //igualdad de listas
  println("igualdad con eq: " + (lista1 eq lista2)) //false, objets distintos
  println("igualdad con ==: " + (lista1 == lista2)) // true, mira el contenido

  //igualdad de listas vacias

  val aNil:List[String] = Nil
  val bNil:List[Double] = Nil
  println("aNil eq bNil: " + (aNil eq bNil)) // True, al estar vacia da igual el tipo de lista

  //uso de head y tail
  val head = lista1.head
  val tail = lista1.tail
  println("cabeza : " + head + ", cola : " + tail)

  //acceso con parentesis

  val primero = lista1(0)
  println("primero : " + primero)

  //tratamiento de excepciones

  try{
    println(lista1(5))
  }catch {
    case e: IndexOutOfBoundsException => println("indice fuera de rango")
    case _ => println("otra excepcion")

  }

  //listas inmutables : operaciones sobre listas generan nuevos objetos
  val lista3=List(1,3,5,7,9)
  val lista4 = lista3.filterNot(v => v==5)

  println("lista3 : " + lista3)
  println("lista4 : " + lista4)

  //uso de map
  val lista3por2 = lista3 map {v => v*2}
  println("lista3por2 = " + lista3por2)

  //val lista3por2 = lista3 map {_*2} lo mismo que lo de antes

  //filatrado normal

  val divisibles3 = lista3 filter (x=> x%3 ==0)


  //reduccion de colecciones
  val suma = lista3 reduceLeft(_ + _)
  val suma2 = lista3.reduceLeft((el1 , el2) => {
    println(el1 + " , " + el2)
    (el1 + el2)
  })

  //versiones con foldleft
  val suma3 = lista3.foldLeft(0)(_ + _)
  val multiplicacion = lista3.foldLeft(1)(_*_)  // raro ()()

  //generar lista a partir de rangos
  val listaRango=(0 to 100).toList
  println("listaRngo : " + listaRango)

  //operaciones de agregacion
  val lista5 = 0 :: lista3
  println("lista5 : " + lista5)

  //agregar 2 listas
  val lista6 = (lista1:::lista2).sorted



  //tuplas
  val tupla1 = (1, "lunes", 2.8, "c", false)
  println("elemento : " + tupla1._3)

  //inversion de elementos
  val tupla2 = (1, "lista")
  val tupla3 = tupla2.swap

  println("tupla3 : " + tupla3)







}



// Main tradicional
/*
object Ejecutable {

  def main(args : Array[String]) : Unit = {

  }
}
*/