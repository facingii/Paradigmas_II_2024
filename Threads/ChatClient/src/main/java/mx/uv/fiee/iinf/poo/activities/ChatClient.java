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
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends JFrame {
    public static String ADDRESS = "localhost";
    public static int PORT = 9888;

    JTextPane chat;
    StyledDocument document;
    SimpleAttributeSet left, right;
    DataInputStream din;
    DataOutputStream dos;

    boolean doChat = false;

    public ChatClient () {
        setTitle ("ChatClient Window");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (450, 400);

        JMenuBar menuBar = new JMenuBar ();

        //Menu section
        JMenu mnuFile = new JMenu ("File");

        JMenuItem itemOpen = new JMenuItem ("Open");
        itemOpen.addActionListener (e -> {
            var fc = new JFileChooser();
            int retVal = fc.showOpenDialog (ChatClient.this);

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

        left = new SimpleAttributeSet();
        StyleConstants.setAlignment (left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground (left, Color.RED);

        right = new SimpleAttributeSet ();
        StyleConstants.setAlignment (right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground (right, Color.BLUE);

        //bottom section
        JPanel panel = new JPanel ();
        panel.setLayout (new FlowLayout());

        JLabel lblSend = new JLabel ("Text");
        JTextField tfSend = new JTextField (20);

        JButton btnSend = new JButton ("Send");

        btnSend.addActionListener (e -> {
            try {
                String line1 = tfSend.getText ();

                dos.writeUTF (line1);
                dos.flush ();

                document.insertString (chat.getText().length(), tfSend.getText () + "\n", left);
            } catch (Exception ex) { ex.printStackTrace (); }
        });

        JButton btnReset = new JButton ("Reset");

        panel.add (lblSend);
        panel.add (tfSend);
        panel.add (btnSend);
        panel.add (btnReset);

        setLayout (new BorderLayout());

        add (BorderLayout.NORTH, menuBar);
        add (BorderLayout.CENTER, chat);
        add (BorderLayout.SOUTH, panel);

        setVisible (true);

        setupSocket ();
    }

    void setupSocket () {
        try {
            Socket socket = new Socket (ADDRESS, PORT);

            din = new DataInputStream (socket.getInputStream ());
            dos = new DataOutputStream (socket.getOutputStream ());

            doChat = true;
            String line2 = "";

            while (doChat) {
                line2 = din.readUTF ();
                document.insertString (chat.getText().length (), line2, right);
            }

            din.close ();
            dos.close ();
            socket.close ();

        } catch (IOException | BadLocationException un) {
            un.printStackTrace ();
        }
    }

    void close () {
        doChat = false;
        setVisible (false);
        super.dispose ();
        System.exit (0);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater (ChatClient::new);
    }
}
