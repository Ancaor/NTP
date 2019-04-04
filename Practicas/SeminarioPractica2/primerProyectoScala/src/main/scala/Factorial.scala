object Factorial extends App{

  //funcion factorial
 // @annotation.tailrec si pongo eso se chiva si la funcion de abajo usa tail recursion
  def factorial(x:Int) : Int = {
    if(x == 0) 1
    else x*factorial(x-1) // aqui lo ultimo en hacerse es el * despues de obtener factorial(x-1) por lo que llama a la pila
  }

  //mejora con tail recursion => esto hace que no se utilice la pila y mejora rendimiento de memoria => se basa en que la llamada recursiva sea siempre lo ultimo en hacerse
  @annotation.tailrec
  def factorial1(x:Int, acumulador:Int) : Int = {
    if(x == 0) acumulador
    else factorial1(x-1,acumulador*x)
  }

  val factorial4 = factorial1(4,1)

  //Como no es natural que reciva 2 argumentos usaremos una funcion auxiliar interna para ocultarlo

  def factorial2(x:Int):BigInt = {
    @annotation.tailrec
    def auxiliar(x:BigInt, acumulador:BigInt): BigInt={
    if(x == 0) acumulador
    else auxiliar(x-1,acumulador*x)
    }

    //desencadena la ejecucion
    auxiliar(x,1)
  }

  val factorial30 = factorial2(30)
  println(factorial30)


  def factorialTRVD(x:BigInt,acum:BigInt=1):BigInt={
    if(x == 0) acum
    else factorialTRVD(x-1,acum*x)
  }

  val factorial10 = factorialTRVD(10)
  println(factorial10)
  val factorial10raro = factorialTRVD(acum = 12, x= 30) //funciona si el orden es incorrecto hay que escribir los nombres de los argumentos


}
