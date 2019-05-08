package definicionClases

class Coche(val marca:String, var enUso:Boolean){

  def reservar(flag:Boolean) ={
    enUso=flag
  }

}

class Renault(val color:String, enUso:Boolean) extends Coche("Renault", enUso)



object Coche extends App{
  val c1 = new Coche("Toyota", true)
  val c2 = new Renault("rojo", false)

  println(s"Vehiculo: ${c1.marca}, estado: ${c1.enUso}")
  println(s"Vehiculo: ${c2.marca}, estado: ${c2.enUso}, color: ${c2.color}")
}
