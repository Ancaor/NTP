package Pruebas;



import org.junit.BeforeClass;
import org.junit.Test;



import PracticaEmpleados.ListaEmpleados;


import java.io.IOException;

import static org.junit.Assert.assertTrue;


/**
 * Práctica 1 NTP
 */
public class ListadoTestP1 {
   private static ListaEmpleados listado;

   /**
    * Codigo a ejecutar antes de realizar las llamadas a los métodos
    * de la clase; incluso antes de la propia instanciación de la
    * clase. Por eso el método debe ser estatico
    */
   @BeforeClass
   public static void inicializacion() throws Exception {
      System.out.println("Metodo inicializacion conjunto pruebas");
      // Se genera el listado de empleados
      try {
         listado = new ListaEmpleados("./data/datos.txt");
      } catch (IOException e) {
         System.out.println("Error en lectura de archivo de datos");
      };
   }

   /**
    * Test para comprobar que se ha leido de forma correcta la
    * informacion de los empleados
    *
    * @throws Exception
    */
   @Test
   public void testConstruccionListado() throws Exception {
      assertTrue (listado.obtenerNumeroEmpleadosArchivo() == 5000);
   }

   /**
    * Test para comprobar la deteccion de dnis repetidos
    */
   @Test
   public void testComprobarHayDnisRepetidos() {

      assertTrue (listado.hayDnisRepetidosArchivo() == true);
   }

   /**
    * Test para comprobar el numero de empleados con correos
    * repetidos
    */
   @Test
   public void testComprobarContadoresDnisRepetidosArchivo() {

      //assertTrue (listado.contarEmpleadosDnisRepetidos() == 4);
   }
}