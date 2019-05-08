package definicionClases

class ClaseGenerica[A](val lista:List[A]) extends Traversable[A]{

  def foreach[B](f: A => B) = lista.foreach(f)

  def map[B](f: A => B) = lista.map(f)
}

object Prueba extends App{


  val obj1 = new ClaseGenerica[String](List("cadena1", "cadena2"))

  obj1.foreach(println)
  obj1 foreach println

  def funcion(cadena: String) = cadena.reverse

  val resultado = obj1.map(funcion(_))
  val resultado2 = obj1 map funcion _




  val obj2 = new ClaseGenerica[Double](List(9.8, 7.5))

  def funcion2(x:Double) = x*x

  val resultado3 = obj2.map(funcion2(_))
  val resultado4 = obj2 map funcion2 _



}
