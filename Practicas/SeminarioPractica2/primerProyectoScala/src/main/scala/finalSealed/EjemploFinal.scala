package finalSealed

class A(final val datoFinal:Int, val datoNormal:Int) {
  final override def toString : String = {
    s"dato final: $datoFinal - dato normal: $datoNormal"
  }
}


//class B(val datoFinal:Int, data1:Int) extends A(datoFinal,data1)  // error porque datoFinal esta en A como final por lo que no puedo sobreescribirlo


class C (override val datoNormal:Int) extends A(5,datoNormal){
  //override def toString = "metodo propio de la clase c"
}

///// Final esta evitando la herencia entre clases


final class D

//class E extends D     // peta porque D en su totalidad es Final

sealed class F

class G extends F     //no peta en el ambito del archivo
// prueba en el archivo controlAcceso2 -> PruebaAccesibilidad





object EjemploFinal extends App {

}
