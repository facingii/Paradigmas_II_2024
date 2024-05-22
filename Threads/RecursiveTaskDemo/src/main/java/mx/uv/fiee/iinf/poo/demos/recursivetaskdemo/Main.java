package mx.uv.fiee.iinf.poo.demos.recursivetaskdemo;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ArrayCounter extends RecursiveTask<Integer> {
    int [] array;
    int threshold = 100_000;
    int start;
    int end;

    public ArrayCounter (int [] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Integer compute() {
        if (end - start < threshold) {
            return computeDirectly ();
        } else {
            int middle = (end + start) / 2;
            
            ArrayCounter subTask1 = new ArrayCounter (array, start, middle);
            ArrayCounter subTask2 = new ArrayCounter (array, middle, end);
            
            invokeAll (subTask1, subTask2);
            
            return subTask1.join () + subTask2.join ();
        }
    }
    
    protected Integer computeDirectly () {
        Integer count = 0;
        
        for (int i = start; i < end; i++) {
            if (array [i] % 2 == 0) {
                count++;
            }
        }
        
        return count;
    }
    
}

public class Main {
    private static final int SIZE = 100_000_000;
    private static final int [] array = randomArray ();
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis ();       
        
        ArrayCounter mainTask = new ArrayCounter (array, 0, SIZE);
        ForkJoinPool pool = new ForkJoinPool ();
        Integer amount = pool.invoke (mainTask);

        long endTime = System.currentTimeMillis ();       
        long time = endTime - startTime;
        
        System.out.println ("Number of array items counted: " + amount);
        System.out.println ("Execution time: " + time + " ms");        
    }
    
    private static int [] randomArray () {
        int [] array = new int [SIZE];
        Random random = new Random ();
        
        for (int i = 0; i < SIZE; i++) {
            array [i] = random.nextInt (100);
        }
        
        return array;
    }
    
}
