package datosMetodos


class PuntoAleatorio{
  val x = {
    println("Asignacion de X")
    util.Random.nextInt
  }

  // Dato miembro Lazy hace que no se construya hasta que no sea necesario
  lazy val y = {
    println("Asignacion de Y")
    util.Random.nextInt
  }
}


object PruebaLazy extends App{

  val p1 = new PuntoAleatorio
  println("Finaliza la construccion del objeto p1")
  println("Valor de componente y: " + p1.y)                         // Al ser lazy, no se inicializa hasta que no se necesite
}
