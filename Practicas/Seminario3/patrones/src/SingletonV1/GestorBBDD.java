package SingletonV1;

public class GestorBBDD {
    private static GestorBBDD gestor = null;
    private static int contadorInstancias = 0;

    //para no permitir creacion de objetos desde fuera de clase
    //se hace el constructor privado
    private GestorBBDD(){
        contadorInstancias++;
    }

    //permite acceso a la unica instancia de la clase
    public static synchronized GestorBBDD obtenerGestor(){
        if(gestor==null)
            gestor = new GestorBBDD();

        return gestor;
    }

    public static int getContadorInstancias(){
        return contadorInstancias;
    }
}
