package Estrategia;

public class AccesoHuella implements ComportamientoAcceso {
    @Override
    public void acceder() {
        System.out.println("Acceso mediante huella...");
    }
}
