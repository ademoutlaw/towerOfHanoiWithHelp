/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public  class ConfirmPanel extends JPanel{
    
    public static final char START = 's';
    public static final char CONTINUE = 'c';
    
    private final JPanel panel;
    private final JButton button1;
    private final JButton button2;
    private final JLabel message;
    private char type;
    private int h;
    private int w;
    private final Color black;


    public ConfirmPanel() {
        black = new Color(0,0,0,200);
        type = START;
        panel = new JPanel();
        button1 =  new JButton();
        button2 = new JButton(); 
        message = new JLabel();
        message.setText("Are You Sure Want To Restart?");
        setBackground(black);
        //setOpaque(false);
        panel.add(message);
        panel.add(button1);
        panel.add(button2);
        Dimension d = new Dimension(500,200);
        panel.setOpaque(false);
        panel.setSize(d);
        
        setLayout(null);
        add(panel);
        
        
        //message.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        paintDialog(g);
        
        
    }
    
    protected void setConfirmType(char type) {
        this.type = type;   
        drawComponent();
    }

    protected void addListener(ActionListener action) {
        button1.addActionListener(action);
        button2.addActionListener(action);
    }

    private void drawComponent() {
        
        switch(type){
            case START:
                drawStart();
                break;
            case CONTINUE:
                drawContinue();
                break;
        }
        
       
    }

    private void drawStart() {
        message.setText("<html><p>The goal of the puzzle is to move all the disks<br> from the leftmost peg "
                + "to the rightmost peg, adhering to the following rules:</p>"
                + "<p>--- Move only one disk at a time.</p>"
                + "<p>--- A larger disk may not be placed ontop of a smaller disk.</p>"
                + "<p>--- All disks, except the one being moved, must be on a peg.</p>"
                + "</html>");
        //<html><p><b>The goal of the puzzle is to move all the disks from the leftmost peg to the rightmost peg, adhering to the following rules:\n" +
        //"\n" +
        //"Move only one disk at a time.\n" +
        //"A larger disk may not be placed ontop of a smaller disk.\n" +
        //"All disks, except the one being moved, must be on a peg.</b></p></html>");
        message.setLocation(200, 60);
        button1.setText("START");
        button1.setLocation(50, 50);
        button1.setActionCommand(GameFrame.START);
        button2.setVisible(false);
    }

    private void drawContinue() {
        message.setText("Dou you want continue or start a new game?");
        button1.setText("new game");        
        button2.setText("continue");
        button2.setVisible(true);
        message.setLocation(150, 50);        
        button1.setLocation(180, 70);
        button2.setLocation(90, 70);
        
        button1.setActionCommand(GameFrame.NEW_GAME);
        button2.setActionCommand(GameFrame.CONTINUE);
        
    }

    private void paintDialog(Graphics g) {
        if(w != getWidth() || h != getHeight()){
            w = getWidth();
            h = getHeight();
            panel.setLocation(w/2-250, h/2-100);
            
        }
        //g.setColor(black);
        //g.fillRect(0, 0,w, h);
        g.setColor(Color.red);
        g.fillRoundRect(w/2-250, h/2-100, 500, 200, 50, 50);
        
    }

    
    
}
