20+10

val x= 5*20

val cantidad={val x = 50*20; x + 10}

{val a=1; {val b= a*2; {val c= b+4; c}}}

//expresion if como sentencia

if(47 % 3 > 0) println("No es multiplo")

val z = 3
val y = 10

val max= if(z > y) z else y

val max1=if(z > y) true

//expresiones match
val maximo2 = x > y match{
  case true => x
  case false => y

}

val error =  500

val mensaje = error match{
  case 200 => "OK"
  case 400 => {
    println ("Error de ejecucion")
    "Error 400"
  }
  case 500 => {
    println("Error sintactico")
    "Error sintactico"
  }
  case _ => "Error desconocido"
}


val dia = "LUNES"

val laborable = dia match {
  case "lunes" |"martes" | "martes" | "miercoles" => "laborable"
  case "sabado" | "domingo" => "festivo"
  case otraCosa => {
    println("Procesado : " + otraCosa)
    "prefiesta"
  }
}

val p: Float = 12.34f
val q:Any = p

val respuesta = q match {
  case z:String => s"$z - String"
  case z:Double => f"$z%.2f - Double"
  case z:Float => f"$z%.2f - Float"
  case z:Long => s"${z} - Long"
}
