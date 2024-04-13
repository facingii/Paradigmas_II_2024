package mx.uv.fiee.iinf.poo.demos.imagecustomclient;

import mx.uv.fiee.iinf.poo.demos.imagecustomcomponent.ImageCustom;
import mx.uv.fiee.iinf.poo.demos.imagecustomcomponent.RegionSelectedListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entry {

    public static void main(String[] args)  {
        JFrame frame = new JFrame ("Test image custom component");
        frame.setSize (1024, 480);
        frame.setDefaultCloseOperation (3);

        JPanel panel = new JPanel ();
        panel.setPreferredSize (new Dimension (426, 240));
        panel.setBackground (Color.pink);

        File file = new File("/Users/Gonzalo/Downloads/rover.jpg");
        ImageCustom imageCustom = new ImageCustom ();
        imageCustom.setPreferredSize (new Dimension (426, 240));
        imageCustom.setImageFile (file);

        // cuando el usuario selecciona una región en el ImageComponent
        // la pintamos en el jpanel
        imageCustom.setRegionSelectedListener (new RegionSelectedListener () {
            @Override
            public void regionSelected(BufferedImage buff) {
                // obtiene el objeto Graphics usado por JPanel para dibujarse
                // y se envuelve en la clase Graphics2D
                Graphics2D g = (Graphics2D) panel.getGraphics ();

                // la imagen obtenida mediante la selección de región
                // se dibujar en el JPanel
                g.drawImage (buff, 0, 0, buff.getWidth(), buff.getHeight(), null);
                g.dispose (); // libera los recursos gráficos
            }
        });

        frame.setLayout (new FlowLayout ());
        frame.add (imageCustom);
        frame.add (panel);

        frame.setVisible (true);
    }

}
