package mx.uv.fiee.iinf.poo.demos.executorcallable;

import java.sql.Time;
import java.util.concurrent.*;

public class Main2 {

    public static void main(String[] args) {
        String URL = "https://firebasestorage.googleapis.com/v0/b/mymovieswishlist-927ff.appspot.com/o/images%2Florem.txt?alt=media&token=4cbd995f-a6fe-4cb3-b802-55696954c31c";

        FutureTask<String> futureTask = new FutureTask<> (new DownloadText (URL));
        ExecutorService service = Executors.newSingleThreadExecutor ();
        service.submit (futureTask);

        // otros procesos
        // BD

        try {
            System.out.println ("Getting text...");
            String foo = futureTask.get ();
            System.out.println (foo);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace ();
        }

        service.shutdown ();
    }

}

//async / await

