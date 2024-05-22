package mx.uv.fiee.iinf.poo.demos.recursiveactiondemo;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class ArrayTransform extends RecursiveAction {
    int [] array;
    int number;
    int threshold = 100_000;
    int start;
    int end;
    
    public ArrayTransform (int [] array, int number, int start, int end) {
        this.array = array;
        this.number = number;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute () {
        if (end - start < threshold) {
            computeDirectly ();
        } else {
            int middle = (end + start) / 2;

            ArrayTransform subTask1 = new ArrayTransform (array, number, start, middle);
            ArrayTransform subTask2 = new ArrayTransform (array, number, middle, end);

            invokeAll (subTask1, subTask2);
        }
    }

    // transform
    protected void computeDirectly () {
        for (int i = start; i < end; i++) {
            array [i] = array [i] * number;
        }
    }
}

public class Main {
    static final int SIZE = 100_000_000;
    static int [] array = randomArray ();
        
    public static void main (String [] args) {
        int number = 10;
        
        System.out.println ("First 10 elements of the array before: ");
        print ();
        
        long start = System.currentTimeMillis ();  
        
        ArrayTransform mainTask = new ArrayTransform (array, number, 0, SIZE);
        ForkJoinPool pool = new ForkJoinPool ();
        pool.invoke (mainTask);
        
        long end = System.currentTimeMillis ();
        long time  = end - start;        
        
        System.out.println ("First 10 elements of the array after: ");
        print ();
        
        System.out.println ("Execution time: " + time + " ms");                
        
    }
    
    private static int [] randomArray () {
        int [] array = new int [SIZE];
        Random random = new Random ();
        
        for (int i = 0;  i < SIZE; i++) {
            array [i] = random.nextInt (100);
        }
        
        return array;
    }
    
    private static void print () {
        for (int i = 0; i < 10; i++) {
            System.out.print (array [i] + ", ");
        }
        
        System.out.println ();
    }
    
}

