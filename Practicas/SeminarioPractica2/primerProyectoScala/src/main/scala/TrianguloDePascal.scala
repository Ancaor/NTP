object TrianguloDePascal extends App{

  def calcularNumero(fila:Int,columna:Int): Int = {
    if(columna == 0 || columna == fila) 1
    else calcularNumero(fila-1,columna-1) + calcularNumero(fila-1,columna)
  }


  for(fila <- 0 to 10){
    for(columna <- 0 to fila){
      print(calcularNumero(fila,columna) + " ")
    }
    println()
  }


}
