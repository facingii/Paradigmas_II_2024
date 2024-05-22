package mx.uv.fiee.iinf.poo.demos.executorcallable;

import java.util.concurrent.*;

public class Main3 {

    public static void main (String [] args) {
        String URL = "https://firebasestorage.googleapis.com/v0/b/mymovieswishlist-927ff.appspot.com/o/images%2Florem.txt?alt=media&token=4cbd995f-a6fe-4cb3-b802-55696954c31c";

        ExecutorService service = Executors.newSingleThreadExecutor();
        MyAsync async = new MyAsync(new DownloadText (URL));
        async.setTaskDoneListener (new TaskDone() {
            @Override
            public void finish () {
                try {
                    String bar = async.get ();
                    System.out.println (bar);
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        });

        System.out.println ("Submiting...");
        service.submit (async);
        System.out.println ("Executing...");
        service.shutdown ();
        System.out.println ("Scheduler close...");

        System.out.println ("Counting within main thread");
        for (int i = 0; i < 10; i++) {
            System.out.println (i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) { ex.printStackTrace (); }
        }
    }

}

class MyAsync extends FutureTask<String> {
    private TaskDone listener;

    public MyAsync (Callable<String> callable) {
        super (callable);
    }

    public void setTaskDoneListener (TaskDone listener) {
        this.listener = listener;
    }

    @Override
    protected void done () {
        super.done ();
        listener.finish ();
    }

}

interface TaskDone {
    void finish ();
}