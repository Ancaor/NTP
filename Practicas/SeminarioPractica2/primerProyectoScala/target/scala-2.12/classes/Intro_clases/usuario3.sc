// Versión mas simple. Se declara el dato miembro a la vez que se pasa el argumento

class Usuario(val nombre:String){
  def prompt: String=nombre+">"

  override def toString: String = s"Usuario($nombre)"
}

val usuario4 = new Usuario("Ruben")

// Si se puede acceder a nombre desde un objeto
usuario4.nombre


// Creacion de una lista de usuarios
val usuarios = List(new Usuario("Administrador"),
                    new Usuario("Operador"),
                    new Usuario("GestorBD")
)

val longitudes = usuarios map (_.nombre.size)
val longitudes2 = usuarios map (usuario => usuario.nombre.size)
val conD = usuarios.filter(_.nombre.contains("d"))