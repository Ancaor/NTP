def saludo = "Hola"

saludo

def saludo1() : String = "Hola"

saludo1()
saludo1

def multiplicar (x : Int, y : Int):Int = {
  x*y
}

multiplicar(2,3)

def multiplicar1(x:Int,y:Int) = x*y

def quitarBlancosIniciales(s:String) : String = {
  if(s == null){
    println("Cadena nula")
    return null;
  }
  s.trim
}

def imprimirMensage(s:String) = {
  println(s)
}

//funciones se pueden llamar con bloques { ... }

def mostrarValor(x:Int) = {
  println(x)
}

mostrarValor{23*120-56/3}

