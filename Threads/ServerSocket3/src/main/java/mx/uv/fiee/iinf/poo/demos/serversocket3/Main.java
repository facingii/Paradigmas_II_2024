package mx.uv.fiee.iinf.poo.demos.serversocket3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static int PORT = 9888;
    
    public static void main (String [] args) {
        ExecutorService service = Executors.newCachedThreadPool ();
        ServerSocket server = null;
        
        try {
            System.out.println ("Listening...");
            server = new ServerSocket (PORT);

            while (true) {
                System.out.println ("Waiting for connections...");
                Socket socket = server.accept ();
                System.out.println (String.format ("Connection %s accepted...passing socket to cachedThreadPool", socket.toString ()));
                service.submit (new MySocketHandler (socket));
            }
           
        } catch (IOException ex) {
            ex.printStackTrace ();
        } finally {
            if (server != null) {
                try {
                    server.close ();
                } catch (IOException ex) {
                    ex.printStackTrace ();
                }
            }
            
            service.shutdown ();
        }
    }
}

class MySocketHandler implements Runnable {
        Socket socket;
        
        public MySocketHandler (Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run () {
            try {
                DataInputStream din = new DataInputStream (socket.getInputStream ());
                DataOutputStream dos = new DataOutputStream (socket.getOutputStream ());

                InputStreamReader in = new InputStreamReader (System.in);
                BufferedReader buff = new BufferedReader (in);

                String line1 = "", line2 = "";

                while (!line1.equals ("stop")) {
                    line1 = din.readUTF ();
                    System.out.println (String.format ("Client %s says %s", socket.toString (), line1));

                    System.out.print ("Response: ");
                    line2 = buff.readLine ();

                    dos.writeUTF (line2);
                    dos.flush ();
                }

                System.out.println (String.format ("Closing connection %s...Godbye!", socket.toString ()));
                
                //din.close ();
                //dos.close ();
                //buff.close ();
                socket.close ();
            } catch (IOException ex) {
                String message = "Exception occurred in Thread " + Thread.currentThread().getName() + "\n";
                message += "Error message: " + ex.getMessage ();
                System.out.println (message);
            }
        }
    }