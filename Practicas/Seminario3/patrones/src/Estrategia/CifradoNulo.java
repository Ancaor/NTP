package Estrategia;

public class CifradoNulo implements ComportamientoCifrado{

    @Override
    public void cifrar(String texto) {
        System.out.println("Cifrado nulo");
    }
}
