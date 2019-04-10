import java.io.File

object BuscadorArchivos {
  private def archivos=(new File(".")).listFiles

  //metodo para buscar archivos con extension determinada
  def archivosConExension(extension: String) = {
    for(archivo<-archivos
      if(archivo.getName.endsWith(extension))) yield archivo
  }
}