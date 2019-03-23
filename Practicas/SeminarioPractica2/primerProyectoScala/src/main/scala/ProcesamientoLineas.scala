import scala.io.Source

object ProcesamientoLineas {


  def imprimirLineas(nombreArchivo: String) : Unit = {
    val lineas: Seq[String] = Source.fromFile(nombreArchivo).getLines().toList
  }

  def calcularAnchoTamLinea(linea :String) : Int ={
    linea.length.toString.length
  }

  def imprimirLineasv2(nombreArchivo : String) : Unit = {
    var lineas  = Source.fromFile(nombreArchivo).getLines().toList

    //determinar linea mas larga
    var maximoAnchoTam = 0

    for(linea <- lineas ){
      maximoAnchoTam = maximoAnchoTam.max(calcularAnchoTamLinea(linea))
    }

    //lineas.map(linea => calcularAnchoTamLinea(linea)).max funcional


    //se utiliza maximoAnchoTam para rellenar espacios

    for(linea <- lineas){
      val tamLinea = calcularAnchoTamLinea(linea)
      val relleno = " "+(maximoAnchoTam - tamLinea)
      println(relleno + linea.length + "|"+ linea )
    }

  }

  def imprimirLineasv3 ( nombreArchivo : String) : Unit = {
    var lineas  = Source.fromFile(nombreArchivo).getLines().toList

    //determinar linea mas larga

    var lineamaslarga = lineas.reduceLeft((a,b) => if(a.length > b.length) a else b)
    var maximoAnchoTam = calcularAnchoTamLinea(lineamaslarga)

    for(linea <- lineas){
      val tamLinea = calcularAnchoTamLinea(linea)
      val relleno = " "+(maximoAnchoTam - tamLinea)
      println(relleno + linea.length + "|"+ linea )
    }

  }

  def main(args : Array[String]) : Unit = {

    if(args.length >= 0){
      imprimirLineasv3("./ProcesamientoLineas.scala")
    }

  }
}
