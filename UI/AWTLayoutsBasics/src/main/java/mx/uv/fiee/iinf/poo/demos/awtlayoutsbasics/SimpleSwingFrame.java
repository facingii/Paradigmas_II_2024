package mx.uv.fiee.iinf.poo.demos.awtlayoutsbasics;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SimpleSwingFrame extends JFrame {
    
    public SimpleSwingFrame () {
        
        //setLayout (new GridLayout (3, 3));
        
        setTitle ("Ventana Swing");
        setSize (800, 600);
        
        
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton ("Button " + (i + 1));
            add (button);
        }       
        
        setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        setVisible (true);
        
    }
}