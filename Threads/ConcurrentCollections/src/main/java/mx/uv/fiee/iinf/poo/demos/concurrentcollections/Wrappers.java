package mx.uv.fiee.iinf.poo.demos.concurrentcollections;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections; //only locks certain methods like add, avoiding 2 threads can insert items at same time, but no sync iterator
import java.util.ListIterator;

public class Wrappers {
    private final List<Integer> list = Collections.synchronizedList (new ArrayList<> ());
    public Thread t1;
    private Thread t2;

    public Wrappers () {
        for (int i = 0; i < 100_000; i++) {
            list.add (i);
        }
    }
    
    public void update () {
        t1 = new Thread (() -> {
            for (int i = 100_000; i < 200_000; i++) {
                list.add (i);
            }
        });
        
        t1.start ();
    }
    
    public void runIterator () {
        Thread t2 = new Thread (() -> {
            synchronized (list) {
                System.out.println ("List size: " + list.size ()); // list size for when thread-2 started
                ListIterator<Integer> iterator = list.listIterator (); // iterator gets a "snapshot" of list at this moment

                while (iterator.hasNext ()) {
                    Integer number = iterator.next ();
                    System.out.println (number);
                };

//                for (var number: list) {
//                    System.out.println (number);
//                }

//                list.forEach (System.out::println);
            }
        });

        /*try {
            t1.join (); // the only way to go thru the entire list is waiting to it get populated
            t2.start ();
        } catch (InterruptedException e) {
            throw new RuntimeException (e);
        }*/
    }
    
    public static void main (String [] args) throws InterruptedException {
        System.out.println ("Wrappers");

        Wrappers m = new Wrappers ();
        m.update ();
        m.runIterator ();
    }
    
}