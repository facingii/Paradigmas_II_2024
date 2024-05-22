package mx.uv.fiee.iinf.poo.demos.syncthreads;

public class SyncStatement {
    private static int acum = 0;
    private static Object mySyncObj;

    public static void main (String [] args) {
         mySyncObj = new Object ();
        
        Thread t1 = new Thread (() -> {
           for (int i = 0; i < 5;) {
               System.out.println ("Thread ONE trying to adquire lock...");
               synchronized (mySyncObj) {
                   System.out.println ("Lock adquired by Thread ONE...");
                   System.out.println("Thread one -> " + ++acum);
                   
                   try {
                       Thread.sleep (100);
                   } catch (InterruptedException in) { in.printStackTrace (); }
                   
                   i++;
               }
           } 
        });
        
        Thread t2 = new Thread (() -> {
            for (int i = 0; i < 5;) {
                System.out.println ("Thread TWO Trying to adquire lock...");
                synchronized (mySyncObj) {
                    System.out.println ("Lock adquired by Thread TWO...");
                    System.out.println ("Thread two -> " + ++acum);
                    
                    try {
                        Thread.sleep (300);
                    } catch (InterruptedException in) { in.printStackTrace (); }
                    
                    i++;
                }
            } 
        });
        
        Thread t3 = new Thread (() -> {
           for (int i = 0; i < 5;) {
                System.out.println ("Thread THREE trying to adquire lock...");
               synchronized (mySyncObj) {
                    System.out.println ("Lock adquired by Thread THREE...");
                    System.out.println ("Thread three -> " + ++acum);
                    
                    try {
                        Thread.sleep (300);
                    } catch (InterruptedException in) { in.printStackTrace (); }
                    
                    i++;
               }
           } 
        });        

        Thread t4 = new Thread (() -> {
           for (int i = 0; i < 5;) {
               System.out.println ("Thread FOUR trying to adquire lock...");
               synchronized (mySyncObj) {
                    System.out.println ("Lock adquired by Thread FOUR...");
                    System.out.println ("Thread four -> " + ++acum);
                    
                    try {
                        Thread.sleep (300);
                    } catch (InterruptedException in) { in.printStackTrace (); }
                    
                    i++;
               }
           } 
        });        
        
        t1.start ();
        t2.start ();
        t3.start ();
        t4.start ();
    }
    
}