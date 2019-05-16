package usoTraits

class Personaje (val nombre:String){
  def sufijo = ""

  override def toString=s"$nombre$sufijo"

}

//este trait solo puede usarse con clases que derivan de personaje

trait Elfo{self: Personaje =>
  override def sufijo: String = "-(elfo)"
}

trait Mago{self: Personaje =>
  override def sufijo: String = "-(mago)"
}

trait Aprendiz{self: Personaje =>
  override def sufijo: String = "-(aprendiz)"
}

//stackable traits
object  EjemploUso extends App{
  val obj1 = new Personaje("Harry Potter") with Mago with Aprendiz //prevalece el ultimo que defino ya que ambos redefinen el mismo método
  val obj2 = new Personaje("Dobby") with Elfo
  println(obj1)
  println(obj2)

}
