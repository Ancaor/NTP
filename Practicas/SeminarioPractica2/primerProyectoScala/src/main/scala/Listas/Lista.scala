package Listas

sealed trait Lista[+A]


//para usar pattern matching

case object Nil extends Lista[Nothing]

case class Cons[+A](cabeza:A, cola:Lista[A]) extends Lista[A]


object Lista {
  def apply[A](elementos: A*) : Lista[A] = {
    if (elementos.isEmpty) Nil
      // _* permite pasar una secuencia de elementos a las funciones con
      // argumentos variables
    else Cons(elementos.head,apply(elementos.tail : _*))
  }

  def length[A] (lista:Lista[A]) : Int = {
    lista match {
      case Nil => 0
      case Cons(head,tail) => 1 + length(tail)
    }
  }

  def suma (lista:Lista[Int]): Int = {
    lista match {
      case Nil => 0
      case Cons(head,tail) => head + suma(tail)
    }
  }

  //implementar
  def foldRight[A,B](lista:Lista[A],neutro:B)(funcion: (A,B)=> B) : B = ???
}

object Prueba extends App{
  var lista: Lista[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  val lista2 = Lista(1,2,3,4)
}
