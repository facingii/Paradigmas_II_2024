package mx.uv.fiee.iinf.poo.demos.imagecustomcomponent;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JComponent;

/**
 * Extiende la clase JComponent para crear un componente personalizado
 * que dibuje una imagen dada, como fondo del mismo.
 *
 * Permite la selección de una región de la imagen y la envía como buffer
 * al manejador del evento RegionSelectedListener
 */
public class ImageCustom extends JComponent {
    private int startX = 0, startY = 0, endX = 0, endY = 0;
    private BufferedImage image;
    private RegionSelectedListener listener;
        
    public ImageCustom () {
        this.image = null;

        // agrega un manejador para los eventos de clic del usuario
        addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked (MouseEvent me) {}

            /**
             * se captura la posición del puntero al momento de presionar el botón
             * izquierdo del mouse.
             *
             * @param me fuente del evento buttonpressed
             */
            @Override
            public void mousePressed (MouseEvent me) {
                startX = me.getX ();
                startY = me.getY ();
            }

            /**
             * cuando se libera el botón se obtiene la porción de la imagen definida
             * por las coordenadas establecidas por el clic inicial y final.
             *
             * para extraer la porción de la imagen se utiliza el método getSubimage de la clase
             * BufferedImage, que requiere un punnto final así como un alto y ancho.
             *
             *
             * @param me fuente del evento buttonreleased
             */
            @Override
            public void mouseReleased (MouseEvent me) {
                int w = endX - startX; // diferencia entre el punto X inicial y final
                int h = endY - startY; // diferencia entre el punto Y inicial y final

                BufferedImage cropImage = image.getSubimage (startX, startY, w, h);
                // si la subimagen es diferente de null y existe un manejador del evento
                // RegionSelectedListener
                if (cropImage != null && listener != null) {
                    listener.regionSelected (grayScale (cropImage)); // se invoca al método regionSelected
                }
            }

            @Override
            public void mouseEntered (MouseEvent me) {}

            @Override
            public void mouseExited (MouseEvent me) {}
        });

        // añade un manejador para el evento de movimientos del mouse
        addMouseMotionListener (new MouseMotionListener () {
            /**
             * detecta cuando el mouse se encuentra en un estado de arrastre
             * estos es, presionar el botón sin soltarlo.
             *
             * @param me fuente del evento mousedragged
             */
            @Override
            public void mouseDragged (MouseEvent me) {
                endX = me.getX (); // eje horizontal
                endY = me.getY (); // eje vertical
                getParent ().repaint (); // forza el repintado del componente.
            }

            @Override
            public void mouseMoved (MouseEvent me) {}
        });
    }

    /**
     * Establece el archivo de imagen a dibujar
     * @param f objeto File referente al archivo de imagen
     */
    public void setImageFile (File f) {
        try {
            BufferedImage buff = ImageIO.read (f);
            setImage (buff);
        } catch (IOException ex) {}
    }
    
    private BufferedImage getImage () {
        return this.image;
    }
    
    private void setImage (BufferedImage image) {
        this.image = image;
    }

    /**
     * Asigna el manejador del evento RegionSelectedListener
     *
     * @param listener referencia al objeto que implementa la interfaz RegionSelectedListener
     */
    public void setRegionSelectedListener (RegionSelectedListener listener) {
        this.listener = listener;
    }

    /**
     * Sobreescribe el método paintComponet para dibujar al componente mismo.
     *
     * @param g objeto Graphics proporcionado por el sistema mediante el cuál se dibuja
     */
    @Override
    protected void paintComponent (Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // envuelve el objeto gráfico en la clase Graphics2D

        // si existe alguna imagen en el buffer
        if (image != null) {
            // se dibuja en componente de acuerdo a la dimensión de este
            g2d.drawImage (image, 0, 0, getSize ().width, getSize ().height, null);
        }

        // si tenemos coordenadas x1, y1, x2, y2 dibujamos un rectangulo en gris
        // que sirve como referencia para la selección
        if (startX > 0 && startY > 0 && endX > 0 && endY > 0) {          
            g2d.setColor (Color.LIGHT_GRAY);
            g2d.drawRect (startX, startY, (endX - startX), (endY - startY));
        }
        
        g2d.dispose (); // se liberan los recursos gráficos
        
        super.paintComponent (g); // llamada al evento paint de JComponent
    }
    
    private BufferedImage grayScale (BufferedImage image) {
        BufferedImage gray = new BufferedImage (image.getWidth (), image.getHeight (), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp op = new ColorConvertOp (ColorSpace.getInstance (ColorSpace.CS_GRAY), null);
        op.filter (image, gray);

        return gray;
    }
}