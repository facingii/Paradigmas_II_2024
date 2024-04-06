package mx.uv.fiee.paradigmas.controls;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame ();
        frame.setSize (480, 640);
        frame.setDefaultCloseOperation(3);

        DefaultTableModel model = new DefaultTableModel ();
        model.addColumn ("Nombre");
        model.addColumn ("Apellido");

        JTable table = new JTable (model);
        table.setGridColor (Color.LIGHT_GRAY);
        table.setShowHorizontalLines (true);
        table.setShowVerticalLines (true);
        table.setDefaultEditor (Object.class, null);

        JTextField textField = new JTextField ();
        textField.setPreferredSize(new Dimension(80, 40));

        JButton btnAdd = new JButton ("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().isEmpty()) return;
                String[] parts = textField.getText().split (" ");
                model.addRow (new Object[]{parts[0], parts[1]});
                textField.setText("");
                textField.grabFocus();
            }
        });

        JButton btnDel = new JButton ("Delete");
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow () > -1) {
                    model.removeRow (table.getSelectedRow ());
                }
            }
        });

        JPanel pnlButtons = new JPanel ();
        pnlButtons.add (btnAdd);
        pnlButtons.add (btnDel);

        BorderLayout layout = new BorderLayout ();
        frame.setLayout (layout);
        frame.add (BorderLayout.CENTER, table);
        frame.add (BorderLayout.NORTH, textField);
        frame.add (BorderLayout.SOUTH, pnlButtons);
        frame.setVisible (true);
    }

}
