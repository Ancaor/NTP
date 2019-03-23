//creacion de listas

val lista1 = List(1,2,3)
val lista2 = List(4,5,6)

//concatenacion

val r1 = lista1 ::: lista2

//longitud
r1.length

//agregar elemento
val r2 = 0 :: lista1
println(lista1)

r1.count(x => x%2 == 0)

r1.drop(1)
r1.dropRight(1)
r1.dropWhile(x => x < 3)
val flag = r1.exists(x => x > 10)

val primero = lista1.head
val resto = lista1.tail

lista1 match {
  case head::tail => println(head)
  case Nil => println("vacia")
}

lista1 match {
  case head::tail => head
  case Nil => -1
}

def max( x : Int , y : Int) = if(x>y) x else y

//en funciones recursivas a la fuerza hay que poner el tipo de retorno

