package Adaptador;

public class AdaptadorLib2 implements AdaptadorCifrado {
    private CifradorLibreria2 cifrador;
    public AdaptadorLib2(CifradorLibreria2 cif){
        cifrador = cif;
    }

    @Override
    public void cifrar(String t) {
        cifrador.realizarCifrado(t);
    }
}
