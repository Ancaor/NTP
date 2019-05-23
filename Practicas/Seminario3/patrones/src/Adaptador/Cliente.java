package Adaptador;

public class Cliente {
    private AdaptadorCifrado adaptador;

    public void asignarAdaptador(AdaptadorCifrado ad){
        adaptador = ad;
    }

    public void cifrar(String cadena){
        adaptador.cifrar(cadena);
    }

    public static void main(String args[]){
        CifradorLibreria1 cifrador1 = new CifradorLibreria1();
        CifradorLibreria2 cifrador2 = new CifradorLibreria2();
        AdaptadorLib1 adaptador1 = new AdaptadorLib1();
        AdaptadorLib2 adaptador2 = new AdaptadorLib2(cifrador2);

        Cliente cliente1 = new Cliente();
        cliente1.asignarAdaptador(adaptador1);
        cliente1.cifrar("adsa");

        cliente1.asignarAdaptador(adaptador2);
        cliente1.cifrar("dasd");
    }
}
