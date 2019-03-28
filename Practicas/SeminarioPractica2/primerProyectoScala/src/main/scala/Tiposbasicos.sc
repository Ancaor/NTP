val x=3

x.isInstanceOf[Any]

class NumeroComplejo(val x:Double, val y :Double)
val c1= new NumeroComplejo(2,3)
c1.isInstanceOf[AnyRef]
c1.isInstanceOf[Any]

val nada = ()

"A".hashCode

(5.0 /7.0).toString

val t:Char  = 65

val tEntero = t.asInstanceOf[Int]  // procurar no hacer, no es muy bueno

