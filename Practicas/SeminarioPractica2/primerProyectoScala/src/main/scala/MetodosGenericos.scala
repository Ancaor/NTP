object MetodosGenericos extends App{

  def eliminar[A](lista : List[A]) : List[A] = lista.tail

  val resultado1: List[Int] = eliminar(List(1,2,3,4))
  val resultado2: List[String] = eliminar(List("HOla","PEPE"))

}
