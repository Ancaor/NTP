// Se agrega un parámetro de clase. Hace como un parámetro en un constructor.

class Usuario(nombreUsuario: String){
  val nombre = nombreUsuario
  def prompt: String=nombre+">"

  override def toString: String = s"Usuario($nombre)"
}

val usuario3 = new Usuario("Pepe")


// no se puede acceder a nombreUsuario desde un objeto
/* usuario3.nombreUsuario */

