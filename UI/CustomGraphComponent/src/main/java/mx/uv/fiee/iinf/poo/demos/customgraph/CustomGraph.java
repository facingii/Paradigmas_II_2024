package mx.uv.fiee.iinf.poo.demos.customgraph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

/**
 * Extiende la clase JComponent para crear un component que dibuje una
 * figura geométrica en el mismo.
 *
 * Implementa la interfaz MouseListener para procesar las entradas del mouse
 */
public class CustomGraph extends JComponent implements MouseListener {
    private int radius;
    private Color color;
    private int x = 0, y = 0;
    
    public CustomGraph () {
        //establecemos un manejador para el evento click del mouse
        this.addMouseListener (this);
        
        //radio y color predeterminados
        setRadius (50);
        setColor (Color.MAGENTA);
    }

    /** Getters & Setters */
    public int getRadius () {
        return radius;
    }

    public void setRadius (int radius) {
        this.radius = radius;
    }

    public Color getColor () {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }
    /** Getter & Setters */

    /** métodos definidos por la interfaz MouseListener */
    @Override
    public void mouseClicked (MouseEvent e) {}

    /**
     * evento disparado por la acción de presionar y liberar el botón del mouse
     *
     * @param e fuente del evento clic
     */
    @Override
    public void mousePressed (MouseEvent e) {
        this.x = e.getX (); // eje X
        this.y = e.getY (); // eje Y
       
        this.repaint (); // forza el repintado del componente
    }

    @Override
    public void mouseReleased (MouseEvent e) {}

    @Override
    public void mouseEntered (MouseEvent e) {}

    @Override
    public void mouseExited (MouseEvent e) {}
    /** métodos definidos por la interfaz MouseListener */

    /**
     *  Método sobreescrito que dibuja al componente.
     *
     * @param g objeto Graphics mediante el cuál realizar el dibujado
     */
    @Override
    protected void paintComponent (Graphics g) {
        //si no existen coordenadas x,y se define el centro del componente como tal
        if (this.x == 0 && this.y == 0) {
            this.x = getSize ().width / 2;
            this.y = getSize ().height / 2;
        }
        
        int coordX = this.x - getRadius ();
        int coordY = this.y - getRadius ();
        int width = radius * 2;
        int height = radius * 2;

        // dibuja un círculo a partir de las coordenadas del punto central
        // y el ancho y alto obtenido de la longitud del radio
        g.setColor (getColor ());
        g.fillOval (coordX, coordY, width, height);        
        
        super.paintComponent(g); // llamada al evento paint de la clase padre
    }
}














