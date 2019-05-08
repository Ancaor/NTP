package clasesCase

case class Personaje (nombre:String, heroe:Boolean){ // datos miembro son tipo val



}


object ClasesCase extends App{

  val obj1= new Personaje("Gollum", false)
  val obj2 = Personaje("Gandalf",true)

  //no pueden modificarse los datos miembro
  //obj1.heroe = true

  val comparacion = obj1==obj2
  println(comparacion)

  println(obj1)

  val obj3 = obj1.copy("Sauron")
  println(obj3)

  val resultado = Personaje.unapply(obj3)
  println(resultado)

}