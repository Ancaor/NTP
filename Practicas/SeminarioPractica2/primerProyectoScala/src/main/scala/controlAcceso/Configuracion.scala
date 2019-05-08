package controlAcceso

private [controlAcceso] class Configuracion {
  val url="http://localhost"

}

class Autenticacion{
  private [this] val password="1234"

  def comparar(otro: Autenticacion) : Boolean = {
    false
  }
}

class Usuario(private var password:String) {
  def actualizarPasword(pwd:String) = password = pwd
  def validar(pwd:String) = password == pwd
}

object Prueba extends App{
  val usuario = new Usuario("1234")
  //println("pwd de usuario: " + usuario.password)
}
