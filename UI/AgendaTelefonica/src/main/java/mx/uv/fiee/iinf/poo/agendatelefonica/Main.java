package mx.uv.fiee.iinf.poo.agendatelefonica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase cliente
 */
public class Main {

    public static void main (String [] args) {
        new Agenda ().setVisible (true);
    }

}

/**
 * Clase que representa nuestra interfaz para la agenda telefónica
 */
class Agenda extends JFrame {
    private JTextField tfName; // campos de datos
    private JButton btnAdd, btnDelete, btnSave; // acciones
    private MyTextField tfPhone; //JTextfield personalizado
    // JTable nos sirve para almacenar el listado de datos que deseamos mostrar al usuario
    private JTable table;
    private DefaultTableModel model; // JTable utiliza un modelo para mantener referencia a los datos

    private final String ENTRY_SEPARATOR = "-";
    private final String PAIR_SEPARATOR = ":";
    private final String FILENAME = "Agenda.txt";

    public Agenda () {
        super ("Agenda Telefónica");

        tfName = new JTextField (); // instanciamos al JTextField nombre
        tfPhone = new MyTextField (); // instanciamos al JTextField teléfono
        tfPhone.setToolTipText ("Format: (###) #######"); // establecemos un mensaje de ayuda al usuario

        btnAdd = new JButton ("Add"); // instanciamos al botón ADD
        // manejador del evento clic del botón ADD
        btnAdd.addActionListener (new ActionListener () {
            /**
             * cada vez que el usuario hace clic se ejecuta el método actionPerformed
             *
             * @param e parámetro con información de la fuente del evento
             */
            @Override
            public void actionPerformed (ActionEvent e) {
                // validamos que los campos no estén vacíos
                if (!tfPhone.getText().isEmpty() && !tfName.getText().isEmpty()) {
                    // anexamos los valores de los campos al modelo
                    model.addRow (new String [] {tfName.getText (), tfPhone.getText ()});
                }

                // si alguno de los campos está vacío, pasamos el foco a él
                if (tfName.getText().isEmpty())
                    tfName.grabFocus ();
                else if (tfPhone.getText().isEmpty())
                    tfPhone.grabFocus ();

                tfPhone.setText (null);
                tfName.setText (null);
            }
        });

        btnDelete = new JButton ("Delete"); // instanaciamos al botón ELIMINAR
        // manejador del evento clic del botón ELIMINAR
        btnDelete.addActionListener (new ActionListener () {
            /**
             * cada vez que el usuario hace clic se ejecuta el método actionPerformed
             *
             * @param e parámetro con información de la fuente del evento
             */
            @Override
            public void actionPerformed (ActionEvent e) {
                if (table.getSelectedRow () > -1) // validamos si existe algún elemento seleccionado
                    model.removeRow (table.getSelectedRow ()); // si existe, se elimina del modelo
            }
        });

        btnSave = new JButton ("Save");
        btnSave.addActionListener (event -> {
            StringBuilder builder = new StringBuilder ();

            for (int i = 0; i < model.getRowCount (); i++) {
                String name = (String) model.getValueAt(i, 0);
                String phone = (String) model.getValueAt(i, 1);
                builder.append (String.format ("%s%s%s", name, PAIR_SEPARATOR, phone));
                builder.append (ENTRY_SEPARATOR);
            }

            if (builder.length () <= 0) return;

            OutputStreamWriter writer = null;

            try {
                File agenda = new File (FILENAME);
                FileOutputStream fos = new FileOutputStream (agenda);
                writer = new OutputStreamWriter (fos, StandardCharsets.UTF_8);
                writer.write (builder.toString ());

                JOptionPane.showMessageDialog (this,"Saved!");
            } catch (IOException ex) {
                ex.printStackTrace ();
            } finally {
                try {
                    if (writer != null)
                        writer.close ();
                } catch (IOException ioex ) {
                    ioex.printStackTrace ();
                }
            }
        });
        // definimos la estructura del modelo que será utilizado por el JTable
        model = new DefaultTableModel ();
        model.addColumn ("Nombre"); // constará de dos columnas, nombre
        model.addColumn ("Teléfono"); // y teléfono

        // creamos al JTable y definimos algunas características
        table = new JTable (model);
        table.setGridColor (Color.LIGHT_GRAY);
        table.setShowHorizontalLines (true);
        table.setShowVerticalLines (true);
        table.setDefaultEditor (Object.class, null);

        // como contenedor para los campos de datos usamos un JPanel
        // con un layout tipo GridLayout de 2 x 2
        JPanel pnlFields = new JPanel ();
        pnlFields.setLayout (new GridLayout(2, 2));

        // agreamos los controles al panel de campos de datos
        pnlFields.add (new JLabel ("Nombre: ")); // la etiqueta no requiere más que instanciación con el texto correspodiente
        pnlFields.add (tfName);
        pnlFields.add (new JLabel ("Teléfono: "));
        pnlFields.add (tfPhone);

        // para organizar al panel de campos + el botón de acción ADD, utilizamos un BoxLayout
        // con alineación vertical
        JPanel pnlTOP = new JPanel ();
        BoxLayout boxLayout = new BoxLayout (pnlTOP, BoxLayout.Y_AXIS);
        pnlTOP.setLayout (boxLayout);
        pnlTOP.add (pnlFields);
        pnlTOP.add (btnAdd);

        // al botón ELIMINAR lo colocamos en un contenedor JPanel, con el objetivo de agegar más controles
        // en esa sección posteriormente
        JPanel pnlBOTTOM = new JPanel ();
        pnlBOTTOM.add (btnSave);
        pnlBOTTOM.add (btnDelete);

        // establecemos las dimensiones iniciales
        setSize (new Dimension(300, 400));
        setLayout (new BorderLayout ()); // y definimos al BorderLayout como layout principal

        // colocamos cada uno de los páneles y controles que corresponden a las secciones,
        // arriba, centro, abajo
        // pnlTOP -> pnlFields + botón ADD
        // table -> JTable para el listado
        // pnlBOTTOM -> botón ELIMINAR
        add (BorderLayout.NORTH, pnlTOP);
        add (BorderLayout.CENTER, table);
        add (BorderLayout.SOUTH, pnlBOTTOM);

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // establece el comportamiento de cierre

        load ();
    }

    private void load () {
        Path path = Paths.get (FILENAME);

        if (Files.exists (path)) {
            try {
                BufferedReader reader = new BufferedReader (new FileReader (path.toFile()));
                String data = reader.readLine ();
                String[] pairs = data.split (ENTRY_SEPARATOR);
                for (String pair : pairs) {
                    String[] entry = pair.split(PAIR_SEPARATOR);
                    model.addRow(entry);
                }
            } catch (IOException ex) {
                ex.printStackTrace ();
            }
        }
    }
}