// definir funciones
def max(x : Int, y : Int) : Int = {
  if ( x > y) x
  else y
}

max(3,9)

def max1(x: Int, y: Int)  = {
  if(x>y) x
  else y
}

def max2( x: Int, y : Int) = if(x>y) x else y

def mostrarSaludo = "Hola, mundo"

mostrarSaludo

val f = max2(_,_)

f(2,5)

def mostrarSaludo2 = { // UNIT INDICA QUE NO DEVUELVE NDA
  println("Hola mundo")
}

mostrarSaludo2 // no devuelve nada solo hace el print
