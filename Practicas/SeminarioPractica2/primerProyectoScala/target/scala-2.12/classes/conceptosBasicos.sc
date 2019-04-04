4

//valores

val x=5


//variables
var y = 3.8

// all es un objeto

3.getClass.getName

8.3.getClass.getName
'c'.getClass.getName
"Hola,Pepe".getClass.getName
true.getClass.getName

//declaracion de funciones

val f=(x:Int, y:Int) => x+5
((x:Int, y:Int) => 5).getClass.getName

//x=5  da error porque es val
y=5


val c = 'c'

val array : Array[Int] = new Array(10)
array(1)=8

//no da error porque el val array sigue apuntando al mismo sitio pero
//la clase Array es mutable.

array.foreach(println)

//declaracion de clases

class NumeroComplejo(val x:Double, val y:Double) { // los val son los datos miembro y tiene un constructor con los dos parametros
  println("Creado objeto clase NUmeroCOmplejo")

  override def toString: String = {

  "x: " + x+ ", y: " + y

  }
}
//parece una funcion ya que ademas de declarar la clase nos indica el constructor
//como los datos miembro son val la clase es inmutable
//esta clase es muy sencilla ya que no tiene cuerpo, pero es totalmente funcional

val nc1 = new NumeroComplejo(7.2,8.3)

// al evaluar nc1 da : nc1: NumeroComplejo = NumeroComplejo@511dbe62  esto es porque llama al toString por defecto y devuelve la direcicon de memoria despues del @
//como sobreescribo el toString sale otra cosa

println("Dato miembro x: " + nc1.x)

//  nc1.x = 1     //petaria porque es un val


class NumeroComplejo2(x:Double,y:Double) { // si no les digo si son val o var entonces x e y ya no son datos miembro sino que se cogen solo como argumentos del constructor
  println("Argumento x: " + x)

}


val nc2 = new NumeroComplejo2(2.3,3.4)
// nc2.x    //peta porque x no es dato miembro


