var i : Int = 10

while(i > 0){
  println("Valor i :"+i)
  i=i-1
}


//do while imperativo

i = 10

do{
  println("Valor de i : "+i)
  i=i-1
} while(i >=0)

1+8

// los bucles no producen valores ya que no son expresiones
// el 1+8 o un if o algo asi SI produce resultado (res2)


//ESTILO FUNCIONAL
//for comprehension

for( i<- 1 to 10){
  println("Valor de i : "+i)
}

val rango1 = 1 to 10
val rango2 = 1 until 10
val rango3 = 1 to 10 by 2
val rango4 = 10 to 1 by -2

for(i <- rango1){
  println("Valor de i : "+i)
}

//for con devolucion de valores

for(i <- rango2) yield 1

val resulado = for(i <- rango4) yield 1

val cadenas = for(i <- 1 to 10 ) yield {
  println("iteracion con i = "+i)
  s"Valor de i: $i"
}

val resultado2 = for{i <- 1 to 3
                     j<- 1 to 5} yield i+j

val resultado3 = for(i <- 1 to 3;
                     j <- 1 to 5) yield (i,j)