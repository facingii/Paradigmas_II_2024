package mx.uv.fiee.iinf.poo.demos.delegationeventmodel;

/***
 * 
 * Esta clase hace uso de un objeto de tipo EventSource e invoca su método start, así mismo para ser
 * capaz de recibir las 'notificaciones' del proceso finalizado implementa el método requerido por la interfaz
 * TaskFinalized
 * 
 */
public class Owner /*implements TaskFinalized*/ {

    public Owner () {       
        System.out.println ("Long Process started!");
        var start = System.currentTimeMillis();
        new EventSource (() -> System.out.println ("Received inside lambda - Process Finished!")).start ();
        var end = System.currentTimeMillis();

        var elapsed = end - start;
        System.out.println (elapsed);
        TaskHandler handler = new TaskHandler();
        //new EventSource (handler).start();
        /*new EventSource(new TaskFinalized() {
            @Override
            public void notificate() {

            }
        });*/
    }
    
    /***
     * método abstracto requerido por la interfaz implementada
     */
//    @Override
//    public void notificate () {
//        System.out.println ("Long Process Finished!");
//    }
    
    public static void main (String [] args) {
        new Owner ();
    }
}

class TaskHandler implements TaskFinalized {

    @Override
    public void notificate() {
        System.out.println ("Paradigmas II course");
    }
}