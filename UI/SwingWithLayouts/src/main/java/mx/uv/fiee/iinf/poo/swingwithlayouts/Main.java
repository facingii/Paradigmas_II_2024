package mx.uv.fiee.iinf.poo.swingwithlayouts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {

    public static void main (String [] args) {
        JFrame frame = new JFrame ("Flow Layout");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setSize (800, 200);
        
        frame.setLayout (new FlowLayout (FlowLayout.CENTER, 20, 20));
        
        JButton btnBorder = new JButton ("BorderLayout");
        btnBorder.setPreferredSize (new Dimension (200, 80));
        btnBorder.addActionListener (e -> { new Border (); });
        
        JButton btnGrid = new JButton ("GridLayout");
        btnGrid.addActionListener (e -> { new Grid (); });
        
        JButton btnGridBag = new JButton ("GridBagLayout");
        btnGridBag.addActionListener (e -> { new GridBag (); });
        
        JButton btnBox = new JButton ("BoxLayout");
        btnBox.addActionListener (e -> { new Box (); });
        
        JButton btnChat = new JButton ("Chat Window");
        btnChat.addActionListener (e -> { new Chat (); });
        
        frame.add (btnBorder);
        frame.add (btnGrid);
        frame.add (btnGridBag);
        frame.add (btnBox);
        frame.add (btnChat);
        
        frame.pack ();
        
        frame.setVisible (true);
    }
}

class Border extends JFrame {
    
    public Border () {
        setTitle ("Border Layout");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (400, 300);
        setLayout (new BorderLayout ());
        
        JButton btnNorth = new JButton ("Button 1 (NORTH)");
        JButton btnEast = new JButton ("Button 2 (EAST)");
        JButton btnCenter = new JButton ("Button 3 (CENTER)");
        JButton btnWest = new JButton ("Button 4 (WEST)");
        JButton btnSouth = new JButton ("Button 1 (SOUTH)");
        
        add (BorderLayout.NORTH, btnNorth);
        add (BorderLayout.EAST, btnEast);
        add (BorderLayout.CENTER, btnCenter);
        add (BorderLayout.WEST, btnWest);
        add (BorderLayout.SOUTH, btnSouth);
        
        setVisible (true);
    }
}

class Grid extends JFrame {
    
    public Grid () {
        setTitle ("Grid Layout");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (400, 300);
        setLayout (new GridLayout (3, 2));
        
        JButton btnOne = new JButton ("One");
        JButton btnTwo = new JButton ("Two");
        JButton btnThree = new JButton ("Three");
        JButton btnFour = new JButton ("Four");
        JButton btnFive = new JButton ("Five");
        
        add (btnOne);
        add (btnTwo);
        add (btnThree);
        add (btnFour);
        add (btnFive);
       
        setVisible (true);
    }
}

class GridBag extends JFrame {
    
    public GridBag () {
        setTitle ("GridBag Layout");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (400, 300);
        setLayout (new GridBagLayout ());
        
        GridBagConstraints cons = new GridBagConstraints ();
        
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        add (new JButton ("One"), cons);
        
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        add (new JButton ("Two"), cons);
        
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipady = 50;
        cons.gridx = 0;
        cons.gridy = 1;
        add (new JButton ("Three"), cons);
        
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipadx = 30;
        cons.gridx = 1;
        cons.gridy = 1;
        add (new JButton ("Four"), cons);
        
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 2;
        add (new JButton ("Five"), cons);        
        
        setVisible (true);
    }
}

class Box extends JFrame {
    
    public Box () {
        setTitle ("Box Layout");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (400, 150);
        
        // buttons
        JButton btnOne = new JButton ("One *");
        btnOne.setPreferredSize(new Dimension (100, 50));
        JButton btnTwo = new JButton ("Two ***");
        JButton btnThree = new JButton ("Three *");
        btnThree.setPreferredSize (new Dimension (100, 50));
        JButton btnFour = new JButton ("Four ***");
        JButton btnFive = new JButton ("Five *");
        btnFive.setPreferredSize(new Dimension (100, 50));
        JButton btnSix = new JButton ("Six ***");
        
        // containers
        JPanel pnlLeft = new JPanel ();
        JPanel pnlCenter = new JPanel ();
        JPanel pnlRight = new JPanel ();
        
        pnlLeft.setBorder (BorderFactory.createTitledBorder ("LEFT"));
        pnlCenter.setBorder (BorderFactory.createTitledBorder ("CENTER"));
        pnlRight.setBorder (BorderFactory.createTitledBorder ("RIGHT"));
        
        //setting boxlayout to each panel using vertical arragement
        BoxLayout boxLayoutLeft = new BoxLayout (pnlLeft, BoxLayout.Y_AXIS);
        BoxLayout boxLayoutCenter = new BoxLayout (pnlCenter, BoxLayout.Y_AXIS);
        BoxLayout boxLayoutRight = new BoxLayout (pnlRight, BoxLayout.Y_AXIS);
        
        //setting layout 
        pnlLeft.setLayout (boxLayoutLeft);
        pnlCenter.setLayout (boxLayoutCenter);
        pnlRight.setLayout (boxLayoutRight);
        
        //add two button on first panel aligning to the left 
        btnOne.setAlignmentX (Component.LEFT_ALIGNMENT);
        btnTwo.setAlignmentX (Component.LEFT_ALIGNMENT);
        pnlLeft.add (btnOne);
        pnlLeft.add (btnTwo);
        
        //add two button on second panel aligning to the center 
        btnThree.setAlignmentX (Component.CENTER_ALIGNMENT);
        btnFour.setAlignmentX (Component.CENTER_ALIGNMENT);
        pnlCenter.add (btnThree);
        pnlCenter.add (btnFour);
        
        //add two button on third panel aligning to the right
        btnFive.setAlignmentX (Component.RIGHT_ALIGNMENT);
        btnSix.setAlignmentX (Component.RIGHT_ALIGNMENT);
        pnlRight.add (btnFive);
        pnlRight.add (btnSix);
        
        //to place the three panels we use another layout, flowlayout in this case
        setLayout (new FlowLayout ());
        add (pnlLeft);
        add (pnlCenter);
        add (pnlRight);
        
        setVisible (true);
    }
    
}

class Chat extends JFrame {
    
    public Chat () {
        setTitle ("Chat Window");
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setSize (450, 400);
        
        JMenuBar menuBar = new JMenuBar ();
        
        //Menu section
        JMenu mnuFile = new JMenu ("File");
        JMenuItem itemOpen = new JMenuItem ("Open");
        JMenuItem itemSave = new JMenuItem ("Save as");
        mnuFile.add (itemOpen);
        mnuFile.add (itemSave);
        
        JMenu mnuHelp = new JMenu ("Help");
        
        menuBar.add (mnuFile);
        menuBar.add (mnuHelp);
        
        //center textarea
        JTextArea chat = new JTextArea ();
        
        //bottom section
        JPanel panel = new JPanel ();
        panel.setLayout (new FlowLayout ());
        
        JLabel lblSend = new JLabel ("Text");
        JTextField tfSend = new JTextField (20);
        JButton btnSend = new JButton ("Send");
        JButton btnReset = new JButton ("Reset");
        
        panel.add (lblSend);
        panel.add (tfSend);
        panel.add (btnSend);
        panel.add (btnReset);
        
        setLayout (new BorderLayout ());
        
        add (BorderLayout.NORTH, menuBar);
        add (BorderLayout.CENTER, chat);
        add (BorderLayout.SOUTH, panel);
       
        setVisible (true);
    }
    
}