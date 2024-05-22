package mx.uv.fiee.iinf.poo.demos.concurrentcollections;

import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class Specialized {
    private Vector<Integer> list = new Vector<>();
    
    public Specialized () {
        for (int i = 0; i < 100_000; i++) {
            list.add (i);
        }
    }
    Thread t1;
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
//            ListIterator<Integer> iterator = list.listIterator ();
//
//            while (iterator.hasNext ()) {
//                Integer number = iterator.next ();
//                System.out.println (number);
//            };

            list.forEach (System.out::println);
        });
        
        t2.start ();
    }
    
    public static void main (String [] args) {
        System.out.println ("Specialized");

        Specialized m = new Specialized ();
        m.update ();
        m.runIterator ();
    }
    
}