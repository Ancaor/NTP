//funcion que recibe lista de argumentos variable
def sumar(numeros:Int *) :Int = {
  var total = 0
  for(i <- numeros) total+=i
  total
}

sumar (1)
sumar(1,2,3,4)

//funciones con varias listas de argumentos
//currying
def multiplicar(x:Int)(y:Int)(z:Int) = x*y*z

def multiplicar3 :Int => Int => Int = multiplicar(3)

//multiplicar 3 es una funcion que recibe 2 parametros y fija el 3

def multiplicar34 = multiplicar3(4) // fija 3 y el 4 y deja un argumento libre

println(multiplicar34(1))


def max(x:Int)(y:Int) = if(x>y) x else y

val f = max _


val f1 = max(3) _
val f2 = max(_:Int)(3)
f2(4)
