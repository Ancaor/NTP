package Adaptador;

public class AdaptadorLib1 implements AdaptadorCifrado {
    private CifradorLibreria1 cifrador = new CifradorLibreria1();

    @Override
    public void cifrar(String t) {
        cifrador.cifrar(t);
    }
}
