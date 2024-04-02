package mx.uv.fiee.iinf.poo.shaper2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Shaper extends JPanel {
    private ArrayList<Object> shapes = new ArrayList<> ();
    private Random random = new Random ();
        
    public Shaper (int count, String shape) {
        setBackground (Color.BLACK);
        setPreferredSize (new Dimension (400, 400));
        
        switch (shape) {
            case "Circles":
                for (int i = 0; i < count; i++) {
                    addCircle (390, 390);
                }
                break;
            case "Stars":
                for (int i = 0; i < count; i++) {
                    addStar (380, 380);
                }
                break;
            case "Both":
                int mid = count / 2;
                
                for (int i = 0; i < mid; i++) {
                    addCircle (390, 390);
                }
                
                for (int i = 0; i < mid; i++) {
                    addStar (380, 380);
                }
        }
    }
    
    public void addCircle (int x, int y) {
        shapes.add (new Circle (random.nextInt (x), random.nextInt (y)));
        repaint ();
    }
    
    public void addStar (int x, int y) {
        shapes.add (new Star (random.nextInt (x), random.nextInt (y)));
        repaint ();
    }
    
    public static void main (String [] args) {
        int amount = 100;
        String shape = "Both";
        
        JFrame myWindow = new JFrame ();
        myWindow.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        myWindow.add (new Shaper (amount, shape));
        myWindow.pack ();
        myWindow.setVisible (true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        for (Object shape: shapes) {
            if (shape instanceof Circle) {
                ((Circle) shape).draw (g);
            } else if (shape instanceof Star) {
                ((Star) shape).draw (g);
            }
        }
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
        String [] fonts = ge.getAvailableFontFamilyNames ();
        
        String s = "Circles and Stars";
        //((Graphics2D) g).setRenderingHints (new RenderingHints (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g.setFont (new Font (fonts [1], Font.PLAIN, 44));
        g.drawString (s, 10, getSize ().height - 50);

        g.dispose ();
    }
    
    
}