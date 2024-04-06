package mx.uv.fiee.paradigmas.controls;

import javax.swing.*;
import java.awt.event.ActionListener;

class JListPanel extends JPanel {
    JList<String> list;
    JTextField tf;
    JButton btnAdd;
    JButton btnDelete;
    String[] items = {"red", "orange", "yellow", "green", "blue", "indigo",
            "violet"};

    public JListPanel() {
        super(true);

        list = new JList<String>();
        list.setVisibleRowCount(4);
        DefaultListModel<String> lm = new DefaultListModel<String>();

        for (int i = 0; i < items.length; i++)
            lm.addElement(items[i]);

        list.setModel(lm);

        JScrollPane sp = new JScrollPane(list);
        this.add(sp);

        tf = new JTextField(6);
        this.add(tf);

        btnAdd = new JButton("Add");
        this.add(btnAdd);

        btnAdd.addActionListener((ActionListener) e -> {
            String s = tf.getText().trim();

            if (!s.isEmpty()) {
                DefaultListModel<String> m = (DefaultListModel<String>) (list.getModel());
                boolean foundit = false;

                for (int i = 0; i < m.size(); i++) {
                    if (((String) m.elementAt(i)).equals(s)) {
                        foundit = true;
                        break;
                    }
                }

                if (foundit) {
                    int result = JOptionPane.showConfirmDialog(
                            JListPanel.this,
                            "Add duplicate value?");

                    if (result == JOptionPane.OK_OPTION) {
                        m.addElement(s);
                        tf.setText("");
                    }
                } else {
                    m.addElement(s);
                    tf.setText("");
                }
            }
        });

        btnDelete = new JButton("Delete");
        this.add(btnDelete);

        btnDelete.addActionListener(e -> {
        });
    }
}

public class JListApp {
    public static void main (String [] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new JListPanel());
        frame.setSize(200,300);
        frame.setVisible(true);
    }
}