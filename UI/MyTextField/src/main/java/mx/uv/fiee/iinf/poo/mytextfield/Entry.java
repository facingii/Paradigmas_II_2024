package mx.uv.fiee.iinf.poo.mytextfield;

import javax.swing.*;

public class Entry {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Textfield");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(3);

        MyTextField my = new MyTextField ();
        frame.add(my);
        frame.pack();
        frame.setVisible(true);
    }

}
