package Estrategia;

public class AccesoPassword implements ComportamientoAcceso{
    @Override
    public void acceder() {
        System.out.println("Acceso mediante contraseña...");
    }
}
