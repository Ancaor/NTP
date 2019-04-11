import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll

object TrianguloCheck extends Properties("Triangulo Pascal"){
  private val MAXIMO = 30

  val coordenadasExtremos = for{
    fila <- Gen.choose(0,MAXIMO)
    columna <- Gen.oneOf(0,fila)
  } yield (fila,columna)

  property("Extremos valen 1")={
    forAll(coordenadasExtremos) { (i) => {
      val resultado = TrianguloDePascal.calcularNumero(i._1,i._2)
      println("Fila = " + i._1 + ", columna = " + i._2 + ", numero = " + resultado)
      resultado == 1
    }}
  }


  //generador coordenadas internas
  val coordenadasInteriores = for{
    fila <- Gen.choose(2,MAXIMO)
    columna <- Gen.choose(1,fila-1)
  } yield(fila,columna)

  property("Valores interiores: ") = {
    forAll(coordenadasInteriores) { (i) => {
      val resultado = TrianguloDePascal.calcularNumero(i._1,i._2)
      val supIzquierda = TrianguloDePascal.calcularNumero(i._1-1,i._2-1)
      val supDerecha = TrianguloDePascal.calcularNumero(i._1-1,i._2)
      println("Fila = " + i._1 + ", columna = " + i._2 + ", numero = " + resultado)
      resultado == (supDerecha + supIzquierda)
    }}
  }




}
