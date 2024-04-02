package mx.uv.fiee.iinf.poo.swingwithoutlayouts;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Login");
        frame.setSize (350, 200);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel ();
        frame.add (panel);
        
        setComponents (panel);
        
        frame.setVisible (true);
    }
    
    public static void setComponents (JPanel panel) {
        panel.setLayout (null);
        
        JLabel userLabel = new JLabel ("User");
        userLabel.setBounds (10, 20, 80, 25);
        panel.add (userLabel);
        
        JTextField userText = new JTextField (20);
        userText.setBounds (100, 20, 165, 25);
        panel.add (userText);
        
        JLabel passwordLabel = new JLabel ("Password");
        passwordLabel.setBounds (10, 50, 80, 50);
        panel.add (passwordLabel);
        
        JTextField passwordText = new JTextField (20);
        passwordText.setBounds (100, 50, 165, 25);
        panel.add (passwordText);
        
        JButton loginButton = new JButton ("Login");
        loginButton.setBounds (10, 90, 80, 25);
        panel.add (loginButton);
        
    }
}