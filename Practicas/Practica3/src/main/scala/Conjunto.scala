class Conjunto(val fnCaracteristica: Int => Boolean) {

  /**
    * Crea una cadena con todos los elementos del conjunto
    *
    * Para obtener los elementos se comprueba con cada entero entre -LIMITE hasta LIMITE
    * si satisface la condición del conjunto (esto se hace llamando a this(i) que es equivalente a
    * llamar a apply(i).
    * @return Cadena con los elementos en un formato correcto.
    */

  override def toString: String = {
    val elementos = for (i <- -Conjunto.LIMITE to Conjunto.LIMITE if this(i) ) yield i
    elementos.mkString("{",",","}")
  }

  /**
    * Metodo que indica si un elemento pertenece al conjunto
    * @param elem elemento dado.
    * @return true si el elemento pertenece al conjunto, false en caso contrario.
    */
  def apply(elem: Int): Boolean = {
    fnCaracteristica(elem)
  }

}

/**
  * Companion object que permite trabajar con el conjunto
  */
object Conjunto {

  /**
    * Limite del posible dominio del conjunto :    DOMINIO = [-LIMITE,LIMITE]
    */
  private final val LIMITE = 10

  /**
    * Método para crear objetos de la clase Conjunto
    * @param f Criterio del conjunto
    * @return Conjunto con criterio indicado
    */
    def apply(f:Int => Boolean): Conjunto = new Conjunto(f)

  /**
    * Metodo para crear un conjunto con un unico elemento
    * @param elem Unico elemento que pertenece al conjunto
    * @return Conjunto al que solo pertenece el elemento indicado
    */
    def conjuntoUnElemento(elem:Int) = {
      new Conjunto((x:Int)=> x == elem)
    }

  /**
    * Método para crear un conjunto unión de dos conjuntos dados
    * @param c1 Primer conjunto dado
    * @param c2 Segundo conjunto dado
    * @return Conjunto resultante de la union de c1 y c2
    */
    def union(c1: Conjunto, c2:Conjunto):Conjunto = {
      new Conjunto((x:Int) => (c1.fnCaracteristica(x) || c2.fnCaracteristica(x)) )
    }

  /**
    * Metodo para crear un conjunto interseccion de dos conjuntos dados
    * @param c1 Primer conjunto dado
    * @param c2 Segundo conjunto dado
    * @return Conjunto resultante de la interseccion de c1 y c2
    */
  def interseccion(c1:Conjunto, c2:Conjunto) = {
    new Conjunto( (x:Int) => (c1.fnCaracteristica(x) && c2.fnCaracteristica(x)))
  }

  /**
    * Método para crear un conjunto con la diferencia de dos conjuntos dados
    * @param c1 Primer conjunto dado
    * @param c2 Segundo Conjunto dado
    * @return Conjunto resultante de la diferencia entre c1 y c2
    */
  def  diferencia(c1:Conjunto, c2:Conjunto) = {
    new Conjunto( (x:Int) => (c1.fnCaracteristica(x) && !c2.fnCaracteristica(x)))
  }

  /**
    * Metodo para crear un conjunto a partir de otro que satisface un predicado
    * @param c Conjunto de partida
    * @param f Predicado a satisfacer
    * @return Conjunto resultado de filtrar el conjunto c con el predicado f
    */
  def filtrar(c:Conjunto,f:(Int)=>Boolean) = {
    new Conjunto( (x:Int) => (c.fnCaracteristica(x) && f(x)) )
  }

  /**
    * Método para comprobar si un predicado se cumple para todos los elementos
    * de un conjunto dado
    * @param c Conjunto dado
    * @param predicado Predicado a comprobar
    * @return true si el conjunto completo satisface el predicado y false si no es asi.
    */
  def paraTodo(c:Conjunto ,predicado: (Int)=> Boolean) = {

    @annotation.tailrec
    def go(elem : Int) : Boolean = {
      if(elem == LIMITE+1) true        // Si me he pasado del dominio devuelve true
      else if(!c.fnCaracteristica(elem)) go(elem+1) // Si el elemento no pertenece al conjunto miro el siguiente
      else predicado(elem) && go(elem+1) // Si el elemento pertenece al conjunto compruebo si satisface el predicado y sigo mirando elementos.
    }

    go(-LIMITE)

  }

  /**
    * Metodo para saber si en un conjunto existe un elemento que satisface el predicado dado
    *
    * El metodo se apoya en el anterior.
    * @param c Conjunto de partida
    * @param predicado Predicado a comprobar
    * @return True si existe, false en caso contrario
    */
  def existe(c:Conjunto, predicado: (Int)=> Boolean) = {
    //Compruebo si no para todos los elementos se cumple !predicado(x)
    !paraTodo(c,(x:Int) => !predicado(x))
  }

  /**
    * Método para mapear un conjunto dado con una funcion dada
    * @param c Conjunto de partida
    * @param funcion Funcion dada para mapear
    * @return Conjunto resultante del mapeo de c con la funcion.
    */
  def map(c:Conjunto, funcion : Int => Int) = {

    //El nuevo conjunto es aquel que cada elemento x es igual a un
    //elemento elem del conjunto c tal que x == funcion(elem)

    new Conjunto( (x:Int) => existe(c,elem => x == funcion(elem)) )
  }


}



