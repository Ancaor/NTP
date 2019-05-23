package SingletonV1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tarea implements Runnable{

    @Override
    public void run() {
        GestorBBDD gestor = GestorBBDD.obtenerGestor();

        //simular realizacion de alguna tarea
        for(int i=0; i< 10000; i++);
    }
}

class Prueba{
    public static void main(String args[]){
        //creamos pool de hebras
        ExecutorService ex = Executors.newFixedThreadPool(100);

        //se agregan las tareas
        for(int i=0; i < 100; i++){
            ex.execute(new Tarea());
        }

        //se resetea el ejecutor
        ex.shutdown();

        System.out.println(
        GestorBBDD.getContadorInstancias());

        // a veces da 2 instancias porque una hebra ha llamado al constructor mientras se
        // estaba llmaando en otra hebra lo que origina 2 instancias
    }
}
