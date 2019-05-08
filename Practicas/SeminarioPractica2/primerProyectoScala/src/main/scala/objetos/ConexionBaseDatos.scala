package objetos

//companion object
object ConexionBaseDatos {
  private val url = "jdbc:://localhost"
  private val usuario = "admin"
  private val password = "1234"


  def apply() = new ConexionBaseDatos

}

class ConexionBaseDatos{
  private val propiedades = Map (
    "url" -> ConexionBaseDatos.url,
    "usuario" -> ConexionBaseDatos.usuario,
    "password" -> ConexionBaseDatos.password
  )

  def djd = "dsada"
}


class A1(val a:Int, val b:Int){

  def sumar = a+b

}

object ObjetoHeredaA extends A1(4,3)

class Prueba extends App {
  val conexion = ConexionBaseDatos()
  conexion.djd


  val resultado = ObjetoHeredaA.sumar
}
