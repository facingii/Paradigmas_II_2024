package mx.uv.fiee.iinf.poo.shaper2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

public class Star {
    private int x, y;
    
    public Star (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw (Graphics g) {
        int xPoints [] = {9, 15, 0, 18, 3};
        int yPoints [] = {0, 18, 6, 6, 18};
        
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints (hints);
        
        GeneralPath star = new GeneralPath ();
        
        star.moveTo (xPoints [0] + x, yPoints [0] + y);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo (xPoints [i] + x, yPoints [i] + y);
        }
        star.closePath ();
        
        g2d.setColor (Color.YELLOW);
        g2d.fill (star);
    }
}