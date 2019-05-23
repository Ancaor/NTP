package Estrategia;

public class ControlAcceso {
    private ComportamientoAcceso gestorAcceso;
    private ComportamientoCifrado gestorCifrado;

    public void asignarComportamientoAcceso(ComportamientoAcceso comportamientoAcceso){
        gestorAcceso = comportamientoAcceso;
    }

    public void asignarComportamientoCifrado(ComportamientoCifrado comportamientoCifrado){
        gestorCifrado = comportamientoCifrado;
    }

    public void acceder(){
        gestorAcceso.acceder();
    }

    public void cifrar(String texto){
        gestorCifrado.cifrar(texto);
    }
}
