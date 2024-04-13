package mx.uv.fiee.iinf.poo.agendatelefonica;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTextField extends JTextField {
    private Matcher matcher;

    public MyTextField () {
        super ();

        this.setColumns (13);

        Pattern pattern = Pattern.compile ("\\(\\d{3}\\)\\s{0,1}\\d{7}$");

        addKeyListener(new KeyListener () {
            @Override
            public void keyTyped (KeyEvent e) {
                if (!(e.getKeyChar () >= '0' && e.getKeyChar () <= '9')
                        && e.getKeyChar () != '('
                        && e.getKeyChar () != ')' && e.getKeyChar () != KeyEvent.VK_SPACE) {

                    e.consume ();

                }
            }

            @Override
            public void keyReleased (KeyEvent e) {
                matcher = pattern.matcher (MyTextField.this.getText ());

                if (matcher.find ()) {
                    MyTextField.this.setBackground (Color.WHITE);
                    MyTextField.this.setForeground (Color.BLACK);
                } else {
                    MyTextField.this.setBackground (Color.RED);
                    MyTextField.this.setFont (new Font("Verdana",Font.BOLD, 12));
                    MyTextField.this.setForeground (Color.white);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}
        });
    }

}
