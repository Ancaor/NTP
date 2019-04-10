import scala.annotation.tailrec

object OrdenacionGenerica {
  def ordenado[A](coleccion : Array[A], criterio: (A,A) => Boolean) : Boolean = {
    @tailrec
    def go(indice : Int) : Boolean = {
      //primer caso base
      if(criterio(coleccion(indice),coleccion(indice+1)) == false) false
      else{
        //Segundo caso base
        if(indice == coleccion.length - 2) criterio(coleccion(indice),coleccion(indice+1))
        else(go(indice+1))
      }
    }
    go(0)
  }
  def ordenadoC[A](array:Array[A])(criterio: (A,A) => Boolean) : Boolean = {
    @tailrec
    def go(indice : Int) : Boolean = {
      //primer caso base
      if(criterio(array(indice),array(indice+1)) == false) false
      else{
        //Segundo caso base
        if(indice == array.length - 2) criterio(array(indice),array(indice+1))
        else(go(indice+1))
      }
    }
    go(0)
  }

  val array1 : Array[Int] = Array(1,5,6,7)
  OrdenacionGenerica.ordenado(array1,(x:Int,y:Int) => x>y)

  OrdenacionGenerica.ordenadoC(array1)(_>_)

  val res3: Array[A] => Boolean = OrdenacionGenerica.ordenadoC(_)((x:Int, y:Int) => x > y)

}
