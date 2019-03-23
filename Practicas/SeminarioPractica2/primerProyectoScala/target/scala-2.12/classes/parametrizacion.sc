var saludo = new Array[String](3)

saludo(0) = "Hola "
saludo(1) = "mundo "
saludo(2) = "cruel"

saludo.apply(1) // igual que hacer saludo(1)
saludo.update(0,"Adios ") //asignacion de valores al array

for(i <- 0 to 2) {
  print(saludo(i))
}

(0).to(2).by(1)