object EstiloFuncional {

  def imprimirArgumentos(args: Array[String]): Unit = {
    var i=0

    while( i < args.length ){
      println("args["+i+"] : " + args(i))
      i+=1
    }
  }

  def imprimirFor(args : Array[String]) : Unit = {
    for(arg <- args) println(arg)
  }

  def imprimirDevolucion(args : Array[String]) : String = {
    args.mkString(" - ")
  }

  def main(args : Array[String]) : Unit = {

    //version 1 de impresion de argumentos
    imprimirArgumentos(args)
    assert(imprimirDevolucion(args) == "Hola - pepe")
  }
}
