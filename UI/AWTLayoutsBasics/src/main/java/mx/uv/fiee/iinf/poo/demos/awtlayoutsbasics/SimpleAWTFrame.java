package mx.uv.fiee.iinf.poo.demos.awtlayoutsbasics;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SimpleAWTFrame extends Frame {
    
    public SimpleAWTFrame () {
        setSize (800, 600);
        setTitle ("Ventana AWT");
        
        FlowLayout f = new FlowLayout ();
        f.setAlignment (FlowLayout.LEADING);
        
        setLayout (f);
 
        Button b = new Button ("Press me!");
        b.setSize (300, 300);
        add (b);
        
        b.addActionListener ((ev) -> {
            SimpleSwingFrame s = new SimpleSwingFrame ();
        });
        /*b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        
        Button b1 = new Button ("Don't press me!");
        b1.setBounds(300, 300, 100, 100);
        add (b1);
        

        TextField t = new TextField ();
        t.setColumns (30);
        t.setBounds (60, 60, 300, 300);
        t.setSize (400, 400);
        
        add (t);
        
        setVisible (true);
        
        this.addWindowListener (new WindowListener () {
            @Override
            public void windowOpened (WindowEvent we) {}

            @Override
            public void windowClosing (WindowEvent we) {
                dispose ();
            }

            @Override
            public void windowClosed (WindowEvent we) {}

            @Override
            public void windowIconified (WindowEvent we) {}

            @Override
            public void windowDeiconified (WindowEvent we) {}

            @Override
            public void windowActivated (WindowEvent we) {}

            @Override
            public void windowDeactivated (WindowEvent we) {}

        });
    }
    
}

