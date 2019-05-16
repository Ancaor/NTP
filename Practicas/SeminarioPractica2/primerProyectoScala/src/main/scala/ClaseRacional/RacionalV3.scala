package ClaseRacional

import scala.annotation.tailrec

class RacionalV3 (n:Int,d:Int){
  // controla que d sea distinto de 0 para proceder
  require(d != 0)

  // calculo de mcd
  val mcd = RacionalV3.maximoComunDivisor(n,d)

  //constructor adicional : se llaman this y tienen que terminar llamando al principal
  //con el numero de argumentos que aparece en la clase
  def this(n:Int) = this(n,1)

  //almacenar n y de como datos miembro
  val numerador = n/mcd
  val denominador = d/mcd

  //metodo para sumar dos raionales
  def +(racionalV3: RacionalV3) : RacionalV3 = {
    new RacionalV3(numerador*racionalV3.denominador + racionalV3.numerador*denominador
      ,denominador*racionalV3.denominador)
  }

  def +(x:Int):RacionalV3 = {
    this + RacionalV3(x)
  }

  def <(otro:RacionalV3) : Boolean = {
    numerador*otro.denominador < otro.numerador*denominador
  }

  def ==(otro:RacionalV3) : Boolean = {
    !this.<(otro) && !otro.<(this)
  }

  def max(otro:RacionalV3) : RacionalV3 = {
    if(this.<(otro)) otro
    else this
  }

  override def toString: String = numerador+"/"+denominador


}

object RacionalV3 extends App {

  def apply(n: Int, d: Int): RacionalV3 = new RacionalV3(n, d)

  def apply(n: Int): RacionalV3 = new RacionalV3(n)

  @tailrec
  def maximoComunDivisor(a:Int,b:Int): Int = {
    if(b == 0) a
    else maximoComunDivisor(b,a%b)
  }


  val obj1 = new RacionalV3(4,3)
  println(obj1)
  val obj2 = new RacionalV3(2,3)
  val obj3 = new RacionalV3(4,5)
  println(obj2.+(obj3))
  println(obj2 + obj3)
  println(obj2 <  obj3)
  println(obj2 == obj2)
  println(obj2 max obj3)

  val obj4 = new RacionalV3(5)
  println(obj4)

  val obj5 = new RacionalV3(25,125)
  println(obj5)
  println(obj5 + 2)
}
