package Estrategia;

public interface ComportamientoAcceso {
    public default void acceder(){
        System.out.println("Acceso por defecto...");
    }
}
