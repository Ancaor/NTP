object FuncionesAnidadas extends App{

  def max(x:Int,y:Int,z:Int) : Int = {
    //funcion auxiliar
    def max(x:Int,y:Int):Int={
      if(x > y) x else y
    }

    //desencadenar ejecucion
    max(x,max(y,z))
  }

  val maximo = max(9,3,14)
  println(maximo)

}
