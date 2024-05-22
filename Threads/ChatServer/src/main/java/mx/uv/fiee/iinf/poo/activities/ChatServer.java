package mx.uv.fiee.iinf.poo.activities;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends JFrame {
    public static int PORT = 9888;

    SimpleAttributeSet left, right;
    StyledDocument document;
    JTextPane chat;
    JTextField tfSend;

    DataInputStream din;
    DataOutputStream dos;

    boolean doChat = false;

    public ChatServer() {
        setTitle ("ChatServer Window");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (450, 400);

        JMenuBar menuBar = new JMenuBar ();

        //Menu section
        JMenu mnuFile = new JMenu ("File");

        JMenuItem itemOpen = new JMenuItem ("Open");
        itemOpen.addActionListener (e -> {
            var fc = new JFileChooser ();
            int retVal = fc.showOpenDialog (ChatServer.this);

            if (retVal == JFileChooser.APPROVE_OPTION) {
                var file = fc.getSelectedFile ();
                System.out.println (file.getName ());
            }
        });

        var separator = new JMenuItem ();

        var mnuExit = new JMenuItem ("Exit");
        mnuExit.addActionListener(e -> close ());

        mnuFile.add (itemOpen);
        mnuFile.add (separator);
        mnuFile.add (mnuExit);

        menuBar.add (mnuFile);

        //center textarea
        chat = new JTextPane ();
        document = chat.getStyledDocument ();

        left = new SimpleAttributeSet ();
        StyleConstants.setAlignment (left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground (left, Color.RED);

        right = new SimpleAttributeSet ();
        StyleConstants.setAlignment (right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground (right, Color.BLUE);

        //bottom section
        JPanel panel = new JPanel ();
        panel.setLayout (new FlowLayout ());

        JLabel lblSend = new JLabel ("Text");
        tfSend = new JTextField (20);

        JButton btnSend = new JButton ("Send");

        btnSend.addActionListener (e -> {
            try {
                String line2 = tfSend.getText();
                dos.writeUTF (line2);
                dos.flush ();

                document.insertString (chat.getText().length(), line2 + "\n", left);
            } catch (Exception ex) { ex.printStackTrace (); }
        });

        JButton btnReset = new JButton ("Reset");

        panel.add (lblSend);
        panel.add (tfSend);
        panel.add (btnSend);
        panel.add (btnReset);

        setLayout (new BorderLayout ());

        add (BorderLayout.NORTH, menuBar);
        add (BorderLayout.CENTER, chat);
        add (BorderLayout.SOUTH, panel);

        setVisible (true);

        setupSocket ();
    }

    void setupSocket () {
        try {
            ServerSocket server = new ServerSocket (PORT);
            Socket socket = server.accept ();
            document.insertString (0, "Client Connected!\n", left);

            din = new DataInputStream(socket.getInputStream ());
            dos = new DataOutputStream (socket.getOutputStream ());

            doChat = true;
            String line1 = "";

            while (doChat) {
                line1 = din.readUTF ();
                document.insertString (chat.getText().length (), line1, right);
            }

            din.close ();
            dos.close ();
            server.close ();
        } catch (IOException | BadLocationException ex) {
            ex.printStackTrace ();
        }
    }

    void close () {
        doChat = false;
        setVisible (false);
        super.dispose ();
        System.exit (0);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater (ChatServer::new);
    }

}
