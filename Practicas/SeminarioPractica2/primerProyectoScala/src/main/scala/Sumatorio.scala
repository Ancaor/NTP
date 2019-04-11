import scala.annotation.tailrec

object Sumatorio {
  //Mi implementacion de sumatorio generico // puede que este mal
  def funcion(inf: Int, sup: Int, operacion: (Int, Int) => (Int)) = {
    @tailrec
    def go(x: Int, acumulador: Int): Unit = {
      if (x == sup) {
        operacion(x, acumulador)
      } else {
        go(x + 1, operacion(x, acumulador))
      }

    }

    go(inf, 0)
  }
}


object SumatorioProfesor extends App{

  def identidad(x:Int) = {
    x
  }

  def cuadrado(x:Int) = {
    x*x
  }

  def potencia2(x:Int):Int = {
    if(x == 0) 1
    else 2 * potencia2(x - 1)
  }


  def potencia2TR(x:Int) : Int = {
    @tailrec
    def go(x:Int,acum:Int) : Int = {
      if(x==0) acum
      else go(x-1,2*acum)
    }

    go(x,1);
  }

  def sumatorio(inf:Int,sup:Int,funcion : Int => Int):Int = {
    if(inf > sup) 0
    else funcion(inf) + sumatorio(inf+1,sup,funcion)
  }

  //definir funciones especificas
  def sumatorioEnteros = sumatorio(_,_,identidad)
  def sumatorioCuadrados = sumatorio(_,_,cuadrado)
  def sumatorioPotencias = sumatorio(_,_,potencia2TR)

  println("Sumtorio primera version ---------")
  println("Sumatorio de 1 a 10: " + sumatorioEnteros(1,10))
  println("Sumatorio cuadrados de 1 a 10: " + sumatorioCuadrados(1,10))
  println("Sumatorio potencias de 1 a 10: "+ sumatorioPotencias(1,10))

  def sumatorioV2(funcion: Int => Int) : (Int,Int) => Int = {
    def sumaConLimites(inf:Int,sup:Int) : Int = {
      if(inf > sup) 0
      else funcion(inf) + sumaConLimites(inf+1,sup)
    }

    sumaConLimites
  }

  def sumatorioEnterosV2 = sumatorioV2(identidad)
  def sumatorioCuadradosV2 = sumatorioV2(cuadrado)
  def sumatorioPotenciasV2 = sumatorioV2(potencia2TR)

  println("Sumtorio primera version ---------")
  println("Sumatorio de 1 a 10: " + sumatorioEnterosV2(1,10))
  println("Sumatorio cuadrados de 1 a 10: " + sumatorioCuadradosV2(1,10))
  println("Sumatorio potencias de 1 a 10: "+ sumatorioPotenciasV2(1,10))


  //Mas simplificado con Currying
  def sumatorioV3(funcion : Int => Int)(inf:Int,sup:Int) : Int = {
    if(inf > sup) 0
    else funcion(inf) + sumatorioV3(funcion)(inf+1,sup)
  }

  def sumatorioEnterosV3:(Int,Int) => Int = sumatorioV3(identidad)
  def sumatorioCuadradosV3:(Int,Int) => Int = sumatorioV3(cuadrado)
  def sumatorioPotenciasV3:(Int,Int) => Int = sumatorioV3(potencia2TR)

  println("Sumtorio V3 version ---------")
  println("Sumatorio de 1 a 10: " + sumatorioEnterosV3(1,10))
  println("Sumatorio cuadrados de 1 a 10: " + sumatorioCuadradosV3(1,10))
  println("Sumatorio potencias de 1 a 10: "+ sumatorioPotenciasV3(1,10))


}




