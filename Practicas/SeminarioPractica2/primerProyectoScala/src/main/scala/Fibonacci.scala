object Fibonacci extends App{

  /**
    * Funcion para calcular la sucesion de Fibonacci
    * @param x
    * @return
    */
  def Fibonacci(x:Int) : BigInt = {
    if(x == 0 || x == 1) x
    else {
      Fibonacci(x-2) + Fibonacci(x-1)
    }
  }

  //Ahora como se haria con tail recursion ?
  def FibonacciTR(x:Long) : Long = {
    @annotation.tailrec
    def go(x:Long, prev:Long,act:Long) : Long = {
      if(x == 0) prev
      else go(x-1,act,act+prev)
    }

    //llamada inicial a go
    go(x,0,1)
  }

 // val tiempo = System.nanoTime()
 // val res1 = Fibonacci(44)
 // val tiempo2 = System.nanoTime()
 // println("Tiempo sin RT : " + (tiempo2-tiempo))
  val tiempo3 = System.nanoTime()
  val res2 = FibonacciTR(70)
  val tiempo4 = System.nanoTime()
  println("Tiempo con RT : " + (tiempo4-tiempo3))

}
