import scala.annotation.tailrec

object Funciones {

  //Ejercicio 1. Triangulo de Pascal

  def calcularValorTrianguloPascal(fila: Int, columna: Int): Long = {
    @tailrec
    def factorial(numero : Int, acumulador : Long): Long = {
      if (numero < 0) 0
      else if (numero == 0) 1
      else if (numero == 1) acumulador
      else factorial(numero - 1, (numero).toLong * (acumulador))
    }
    factorial(fila,1) / (factorial(columna,1) * factorial(fila-columna,1))
  }

  // Ejercicio 2. Series

  def FibonacciLucas (x1:Int,x2:Int):Int = {
    x1+x2
  }

  def PellLucas (x1:Int,x2:Int):Int = {
    x1+(2*x2)
  }

  def Jacobsthal (x1:Int,x2:Int):Int = {
    (2*x1)+x2
  }

  def serie (funcion: (Int,Int)=> Int)(t0:Int , t1:Int, t:Int) : Int = {
    @tailrec
    def go(ta:Int,tm2:Int, tm1:Int): Int ={
      if(ta == 0) tm2
      else if(ta == 1) tm1
      else go(ta-1,tm1, funcion(tm2,tm1))

    }
    go(t,t0,t1)

  }

  def chequearBalance(cadena: List[Char]): Boolean = {

    @annotation.tailrec
    def go(cadena: List[Char], acum: Int = 0): Boolean = {

      if(cadena.isEmpty) acum == 0
      else if(acum < 0) false
      else if(cadena.head == '(') go(cadena.tail,acum+1)
      else if(cadena.head == ')') go(cadena.tail,acum-1)
      else go(cadena.tail,acum)

    }

    go(cadena,0)
  }



  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

       // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 25) {
     // Se muestran 10 y 10 filas
     for (col <- 0 to row)
      print(calcularValorTrianguloPascal(row, col) + " ")

     // Salto de linea final para mejorar la presentacion
     println()
     }

     // Se muestra el valor que debe ocupar la columna 5 en la fila 10
     println(calcularValorTrianguloPascal(14, 1))

    //*****************************************

    println("Serie Fibonacci para t=8: ")
    println(serie(FibonacciLucas)(0,1,8))
    println("Serie Lucas para t=6:")
    println(serie(FibonacciLucas)(2,1,6))
    println("Serie Pell para t=6:")
    println(serie(PellLucas)(2,6,2))
    println("Serie Pell-Lucas para t=6:")
    println(serie(PellLucas)(2,2,3))
    println("Serie Jacobsthal para t=6:")
    println(serie(Jacobsthal)(0,1,3))


    /****************************************************/

    val cadena = "())("
    println(chequearBalance(cadena.toList))
  }

}
