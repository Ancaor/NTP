import scala.annotation.tailrec

object TrianguloPascal {

  def calcularValorTrianguloPascal(fila: Int, columna: Int): Int = {
    @tailrec
    def factorial(numero : Int, acumulador : Int): Int = {
      if (numero < 0) 0
      else if (numero == 0) 1
      else if (numero == 1) acumulador
      else factorial(numero - 1, numero * acumulador)
    }

    factorial(fila,1) / (factorial(columna,1) * factorial(fila-columna,1))
  }

  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")
       // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
     // Se muestran 10 y 10 filas
     for (col <- 0 to row)
     print(calcularValorTrianguloPascal(row, col) + " ")

     // Salto de linea final para mejorar la presentacion
     println()
     }

     // Se muestra el valor que debe ocupar la columna 5 en la fila 10
     print(calcularValorTrianguloPascal(10, 5))
     }

}
