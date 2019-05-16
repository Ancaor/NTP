package usoTraits

import scala.collection.mutable.ArrayBuffer

abstract class ColaEnteros {
  def get() : Int
  def put(x:Int) : Unit
}

class ColaBasica extends ColaEnteros{
  private val buffer = new ArrayBuffer[Int]

  def get() = buffer.remove(0)
  def put(x:Int) = buffer+=x
}

trait ColaPor2 extends ColaEnteros {
  abstract override def put(x: Int): Unit = {
    println("put en colapor2")
    super.put(2*x)
  }
}

trait ColaSumando extends ColaEnteros{
  abstract override def put(x: Int): Unit = {
    println("put en colasumando")
    super.put(x+1)
  }
}


trait ColaFiltrando extends ColaEnteros{
  abstract override def put(x: Int): Unit = {
    println("put en cola filtrado")
    if (x>0) super.put(x)
  }
}

class ColaEspecial extends ColaBasica with ColaPor2

object PruebaColas extends App{
  val colaBasica = new ColaBasica
  colaBasica.put(10)
  colaBasica.put(20)
  println(colaBasica.get())
  println(colaBasica.get())

  //stackable traits
  val cola2 = new ColaBasica with ColaPor2
  cola2.put(10)
  cola2.put(20)
  println(cola2.get())
  println(cola2.get())

  val cola3 = new ColaBasica with ColaSumando with ColaFiltrando
  cola3.put(-1)
  cola3.put(0)
  cola3.put(1)
  println(cola3.get())
  //println(cola3.get()) // no se ha introducido el -1 por el filtro
  //println(cola3.get()) // no se ha introducido el 0 por el filtro
}
