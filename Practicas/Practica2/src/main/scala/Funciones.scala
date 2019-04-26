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

    def go(t0:Int , t1:Int, funcion: (Int,Int)=> Int , acumulador:Int, t:Int): Int = {
      if(t == 0) t0
      else if(t == 1) t1
      else if(t == 2) funcion(t0,t1)
      else funcion(go(t0,t1,funcion,0,t-2),go(t0,t1,funcion,0,t-1))
    }

    go(t0,t1,funcion,0,t)

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
    println(serie(Jacobsthal)(0,1,4))
  }

}
