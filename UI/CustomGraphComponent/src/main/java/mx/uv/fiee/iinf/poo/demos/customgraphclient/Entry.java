package mx.uv.fiee.iinf.poo.demos.customgraphclient;

import mx.uv.fiee.iinf.poo.demos.customgraph.CustomGraph;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;

public class Entry {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Test customgraph component");
        frame.setSize (640, 480);
        frame.setDefaultCloseOperation (3);

        // se instancia al custom component
        CustomGraph graph = new CustomGraph ();
        graph.setColor (Color.BLUE); // define el color de la figura
        graph.setRadius (200); // establece el radio

        // añade al nuevo componente a la ventana
        frame.setLayout (new CardLayout ());
        frame.add (graph);
        frame.setVisible (true);
    }

}
