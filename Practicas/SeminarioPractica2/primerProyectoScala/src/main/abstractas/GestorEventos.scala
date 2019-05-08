package abstractas

abstract class GestorEventos {
  def ejecutar
}

class Aplicacion{
  var gestor:GestorEventos = null

  def registrarGestor(objeto:GestorEventos) = {
    gestor = objeto
  }

  def notificar = gestor.ejecutar
}

class GestorConcreto extends GestorEventos{
  def ejecutar ={
    println("Ejecucion de gestor concreto")
  }
}


object PruebaGestor extends App{

  val obj1 = new Aplicacion
  obj1.registrarGestor(new GestorConcreto)
  obj1.notificar

  val obj2 = new Aplicacion
  obj2.registrarGestor(new GestorEventos {
    override def ejecutar: Unit = println(s"Activacion evento en ${new java.util.Date}")
  })

  obj2.notificar

}
