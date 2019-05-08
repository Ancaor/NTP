package datosMetodos

class Multiplicador(val factor:Int) {
  def apply(valor:Int) = valor*factor
}

// Companion object -> objeto con el mismo nombre que la clase
object Multiplicador{
  def apply(factor:Int) = new Multiplicador(factor)
}

object MetodosApply extends App{

  val multiplicador = new Multiplicador(3)
  println("multiplicador por 3x10: " + multiplicador.apply(10))
  println("multiplicador por 3x10: " + multiplicador(10))                 // Con apply se puede ahorrar la llamada al propio metodo


  val lista1 = List(1,2,3,4,5,6,7)
  val dato1 = lista1(4)                                                   // El método al que se llama es apply

  val multiplicador2 = Multiplicador(5)                                   // No requiere el new. Usa el companion object

}
