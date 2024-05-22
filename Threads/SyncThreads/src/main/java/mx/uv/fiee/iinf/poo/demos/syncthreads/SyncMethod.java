package mx.uv.fiee.iinf.poo.demos.syncthreads;

public class SyncMethod {
    private static int acum = 0;
    
    public static void main (String [] args) {
        Thread t1 = new Thread (() -> {
           for (int i = 0; i < 5; i++) {
               increment ();
               System.out.println ("Thread one -> " + acum);
           } 
        });
        
        Thread t2 = new Thread (() -> {
            for (int i = 0; i < 5; i++) {
                increment ();
                System.out.println ("Thread two -> " + acum);
            } 
        });
        
        Thread t3 = new Thread (() -> {
           for (int i = 0; i < 5; i++) {
               increment ();
               System.out.println ("Thread three -> " + acum);
           } 
        });        
        
        Thread t4 = new Thread (() -> {
           for (int i = 0; i < 5; i++) {
               increment ();
               System.out.println ("Thread four -> " + acum);
           } 
        });        
        
        t1.start ();
        t2.start ();
        t3.start ();
        t4.start ();
    }
    
    /***
     * El método sincronizado es adquirido en el objeto entero. Esto significa que ningún otro hilo
     * puede utilizarlo mientras esté siendo ejecutado por el hilo que lo adquirió
     */
    private synchronized static void increment () {
        // vamos a simular la ejecución de una operación lenta
        // detiendo al hilo donde se ejecuta el método, durante 100ms,
        // esto obligara a cada hilo a esperar ese tiempo para acceder al recurso
        try {
            Thread.sleep (100);
        } catch (InterruptedException in) { in.printStackTrace (); }
        
        acum++;
    }
}
