
import org.scalameter.api._
import Funciones.jumpSearch
import Funciones.busquedaBinaria

object BenchmarkAlgoritmosBusqueda
  extends Bench.LocalTime {
  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges = for {
    size <- sizes
  } yield 0 until size

  performance of "Range" in {
    measure method "jumpSearch" in {
      using(ranges) in {
        r => {
          jumpSearch(r.toArray,r.end,(a:Int,b:Int)=> a<b)
        }
      }
    }
    measure method "binarySearch" in {
      using(ranges) in {
        r => {
          busquedaBinaria(r.toArray,r.end,(a:Int,b:Int)=> a<b)
        }
      }
    }
  }
}




