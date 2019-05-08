class Usuario

val usuario1 = new Usuario

val esAnyRef = usuario1.isInstanceOf[AnyRef]                                                                           // Es referencia de Any
val esAny = usuario1.isInstanceOf[Any]                                                                                  // Es un Any


class Usuario2{
  val nombre:String="Administrador"
  def promp:String=nombre+">"


  override def toString: String = s"Usuario($nombre)"
}

val usuario2 = new Usuario2