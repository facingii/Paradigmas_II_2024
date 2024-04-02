package mx.uv.fiee.iinf.poo.shaper2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Circle {
    private int x, y, width, height;
    
    public Circle (int x, int y) {
        this.x = x;
        this.y = y;
        width = 50;
        height = 50;
    }
    
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints (hints);

        Ellipse2D.Double circle = new Ellipse2D.Double (x, y, width, height);
        
        g2d.setColor (Color.PINK);
        g2d.fill (circle);
    }
    
}