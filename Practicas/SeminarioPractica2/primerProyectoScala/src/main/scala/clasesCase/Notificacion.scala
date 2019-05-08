package clasesCase

abstract class Notificacion

case class Email(direccion: String, titulo: String, cuerpo: String) extends Notificacion

case class SMS(numeroOrigen: String, mensaje: String) extends Notificacion

case class MensajeVoz(contacto: String, enlace: String) extends Notificacion

object ClasesCase2 extends App{
  def mostrarNotificacion(notificacion: Notificacion): String = {

    notificacion match {

      case Email(direccion,titulo, _) => "Recibido correo de " + direccion + "con titulo: " + titulo
      case SMS(numero, mensaje) => "Recibido SMS de " + numero + " - Mensaje: " + mensaje
      case MensajeVoz(contacto, enlace) => "Recibido mensaje de voz de " + contacto + " - pulsar enlace para oir: " + enlace
      case _ => {"Tipo de objeto desconocido"}
    }

  }

  val mensajeSMS = SMS("12345", "Estas ahi?")
  val mensajeVoz = MensajeVoz("Tomas",
    "voicerecording.org/id/123")
  println(mostrarNotificacion(mensajeSMS))
  println(mostrarNotificacion(mensajeVoz))

}
