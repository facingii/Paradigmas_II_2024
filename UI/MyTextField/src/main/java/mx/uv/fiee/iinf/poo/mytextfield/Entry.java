package mx.uv.fiee.iinf.poo.mytextfield;

import javax.swing.*;
import java.awt.*;

public class Entry {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("My Custom JTextfield");
        frame.setSize(320, 240);
        frame.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);

        FlowLayout layout = new FlowLayout (FlowLayout.LEFT);
        frame.setLayout (layout);

        JLabel label = new JLabel ("Phone");
        MyTextField my = new MyTextField ();
        my.setPreferredSize (new Dimension(200, 40));

        frame.add (label);
        frame.add (my);
        frame.setVisible(true);
    }

}
