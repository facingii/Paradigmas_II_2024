package mx.uv.fiee.iinf.poo.demos.parallelismdemo;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveTask;

//[ [ subtask1 | subtask2  ] [] [] []    ]

/**
 *
 */
class ArrayCounter extends RecursiveTask<Integer> {
    int [] array;
    int threshold;
    int start;
    int end;
    
    public ArrayCounter (int [] array, int start, int end, int threshold) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }
    
    
    @Override
    protected Integer compute () {
        if (end - start < threshold) {
            return computeDirectly ();
        } else {
            int middle = (end + start) / 2;

            ArrayCounter subTask1 = new ArrayCounter (array, start, middle, threshold);
            ArrayCounter subTask2 = new ArrayCounter (array, middle, end, threshold);

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
    private static final int SIZE = 10_000_000;
    private static int [] array = randomArray ();
    
    public static void main (String [] args) {
        int threshold = 100_000;
        int parallels = 5;
        
        long startTime = System.currentTimeMillis ();
        
        ArrayCounter mainTask = new ArrayCounter (array, 0, SIZE, threshold);
        ForkJoinPool pool = new ForkJoinPool (parallels);
        Integer acum = pool.invoke (mainTask);
        
        long endTime = System.currentTimeMillis ();
        
        System.out.println ("Number of array items acummulated: " + acum);
        
        long time = endTime - startTime;
        System.out.println ("Execution time: " + time + " ms");
    }
    
    private static int [] randomArray () {
        int [] array = new int [SIZE];
        Random random = new Random ();
        
        for (int i = 0;i < SIZE; i++) {
            array [i] = random.nextInt (1000);
        }
        
        return array;
    }
}