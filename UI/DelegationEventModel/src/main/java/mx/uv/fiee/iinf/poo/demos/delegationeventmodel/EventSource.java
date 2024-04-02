package mx.uv.fiee.iinf.poo.demos.delegationeventmodel;

/***
 * 
 * Esta clase es la fuente del evento que estamos interesados en notificar al objetos consumidor,
 * como parámetro del constructor se recibe un objeto que implemente la interfaz TaskFinalized 
 * cuya referencia se almacena en la variable callback.
 * 
 */
public class EventSource {
    private TaskFinalized callback;
    
    public EventSource (TaskFinalized t) {
        callback = t;
    }
    
    /**
     * Inicia el proceso de larga duración y finalizar invoca al método 'notificate' propio de la interfaz
     */
    public void start () {
        doCalc ();
        callback.notificate ();
    }
    
    /***
     * Para simular un proceso de larga duración se hace uso del método sleep de la clase Thread,
     * que detiene la ejecución del hilo prinicipal durante los milisegundos que se pasen como parámetro
     */
    private void doCalc () {
        try {
            Thread.sleep (10000);
        } catch (InterruptedException i) {}
    }
    
}