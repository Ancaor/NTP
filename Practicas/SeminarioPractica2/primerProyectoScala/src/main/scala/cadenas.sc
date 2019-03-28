val saluda = "Hola Pepe"
val despedida = "Hasta luego, \n Lucas"

println(despedida)

//uso de operadores aritmeticos
val saludo1 = "Hola " + "Pepe"
val tarareo = "ta"*10

//comparacion con ==
val iguales=(saludo1=="Hola Pepe")

//cadenas con varias lineas de texto
val comentario =
  """Esto es un comentario muy largo
    | que ocupa varias lineas
  """
val aproximacionPi = 355/113f

println(s"Valor de Pi aproximado: $aproximacionPi") // da formato como un printf
println(f"Valor de pi aproximado: $aproximacionPi%.5f")

