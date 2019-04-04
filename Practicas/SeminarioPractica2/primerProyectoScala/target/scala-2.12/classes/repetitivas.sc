val x = 1 to 6
val y = 1 to 6 by 2
val z = 1 until 10

for(x <- 1 to 7) println(x) // se traduce a map y flatmap internamente pero su escritura es mas natural

//uso cercano al map del for
for(x <- 1 to 7) yield {
  println(x)
  x*x
}

//su equivalente en map
(1 to 7).map( x => {
  println(x)
  x*x
})

val mult3 = for(i <- 1 to 50 if i%3 == 0) yield i

//Procesamiento archivo
val archivos =
  (new java.io.File(".")).listFiles

for ( archivo <- archivos;
  if archivo.isFile;
  if archivo.getName.contains("s")) yield archivo

val datos = for{x <- 1 to 10
  y <- 2 until 8} yield{
    println(s"Iteracion para $x - $y")
    (x,y)
  }

