import java.io.File

object BuscadorArchivos {
  private def archivos=(new File(".")).listFiles

  //metodo para buscar archivos con extension determinada
  def archivosConExension(extension: String) = {
    for(archivo<-archivos
      if(archivo.getName.endsWith(extension))) yield archivo
  }

  def archivosConCadena(cadena : String) = {
    for(archivo<-archivos
        if(archivo.getName.contains(cadena))) yield archivo
  }

  def archivoConExpresionRegular(expresion:String) = {
    for(archivo<-archivos
        if(archivo.getName.matches(expresion))) yield archivo
  }


}


object BuscadorArchivosSimple {
  private def archivos = (new File(".")).listFiles()

  //Patron estrategia
  def archivosCondicion(cadena:String, condicion:(String,String) => Boolean) = {
    for(archivo<- archivos
      if(condicion(archivo.getName,cadena))) yield archivo
  }

  def archivosConExtension(extension:String) = {
    archivosCondicion(extension,_.endsWith(_))
    // archivosCondicion(extension, (cadena1,cadena2) => cadena1.endsWith(cadena2)) // equivalente a la de arriba
  }

  def archivosConCadena(cadena:String) = {
    archivosCondicion(cadena,_.contains(_))
  }

  def archivosConExpresion(expresion:String) = {
    archivosCondicion(expresion,_.matches(_))
  }
}

object BuscadorArchivosMasSimple{
  private def archivos = (new File(".")).listFiles()

  def archivosCondicion(condicion: String => Boolean) = {
    for(archivo <- archivos
      if(condicion(archivo.getName))) yield archivo
  }

  def archivosConExtension(extension:String) = {
    archivosCondicion(_.endsWith(extension))
    //archivosCondicion(cadena => cadena.endsWith(extension))
  }

}