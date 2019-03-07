package benchmark;

import base.ProcesarEmpleados;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class ComparacionTiempoProcesamiento {
   private static ProcesarEmpleados objeto=new ProcesarEmpleados("./data/empleados.txt");

   public static void main(final String[] args) throws Exception {
      org.openjdk.jmh.Main.main(args);
   }

   @Benchmark
   @Fork(value = 1, warmups = 1)
   @Warmup(iterations = 1)
   @Measurement(iterations = 3)
   @OutputTimeUnit(TimeUnit.MILLISECONDS)
   @BenchmarkMode(Mode.AverageTime)
   public void listadoImperativo() {
      //final int[] array = random(SIZE);
      objeto.listadoImperativo();
   }

   @Benchmark
   @Fork(value = 1, warmups = 1)
   @Warmup(iterations = 1)
   @Measurement(iterations = 3)
   @OutputTimeUnit(TimeUnit.MILLISECONDS)
   @BenchmarkMode(Mode.AverageTime)
   public void listadoFuncional() {
      //final int[] array = random(SIZE);
    objeto.listadoFuncional();
   }

   @Benchmark
   @Fork(value = 1, warmups = 1)
   @Warmup(iterations = 1)
   @Measurement(iterations = 3)
   @OutputTimeUnit(TimeUnit.MILLISECONDS)
   @BenchmarkMode(Mode.AverageTime)
   public void listadoFuncionalParalelo() {
      objeto.listadoFuncionalParalelo();
   }

}
