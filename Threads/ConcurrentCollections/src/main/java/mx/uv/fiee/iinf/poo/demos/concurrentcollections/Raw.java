package mx.uv.fiee.iinf.poo.demos.concurrentcollections;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Raw {
    private Object mySync = new Object ();
    private List<Integer> list = new ArrayList<> ();
    
    public Raw () {
        for (int i = 0; i < 100_000; i++) {
            list.add (i);
        }
    }
    
    public void update () {
        Thread t1 = new Thread (() -> {
            for (int i = 100_000; i < 200_000; i++) {
                synchronized (mySync) {
                    list.add (i);
                }
            }
        });
        
        t1.start ();
    }
    
    public void runIterator () {
        Thread t2 = new Thread (() -> {
//            ListIterator<Integer> iterator = list.listIterator ();
//
//            while (iterator.hasNext ()) {
//                Integer number = (Integer) iterator.next ();
//                System.out.println (number);
//            };

            synchronized (mySync) {
                list.forEach (System.out::println);
            }
        });
        
        t2.start ();
    }
    
    public static void main (String [] args) {
        Raw m = new Raw ();
        m.update ();
        m.runIterator ();

        System.out.println("List size: " + m.list.size());
    }
    
}
