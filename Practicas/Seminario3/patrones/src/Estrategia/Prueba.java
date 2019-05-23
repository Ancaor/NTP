package Estrategia;

public class Prueba {
    public static void main(String args[]){
        //crea objeto de control de acceso
        ControlAcceso controlador = new ControlAcceso();

        //se asignan los comportamientos
        controlador.asignarComportamientoAcceso(new AccesoPassword());

        controlador.asignarComportamientoCifrado(new CifradoNormal());

        controlador.acceder();
        controlador.cifrar("texto");

        //de forma dinamica cmbio el comportamiento
        controlador.asignarComportamientoCifrado(new CifradoNulo());
        controlador.cifrar("djad");
    }
}
