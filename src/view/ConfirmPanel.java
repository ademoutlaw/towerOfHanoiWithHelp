/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
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
    
    private final JButton button1;
    private final JButton button2;
    private final JLabel message;
    private char type;


    public ConfirmPanel() {
        type = START;
        button1 =  new JButton();
        button2 = new JButton(); 
        message = new JLabel();
        message.setText("Are You Sure Want To Restart?");
        //setBackground(new Color(0,0,0,200));
        setOpaque(false);
        add(message);
        add(button1);
        add(button2);
        //message.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.fillRoundRect(getWidth()/2-250, getHeight()/2-100, 500, 200, 50, 50);
        drawComponent(getWidth()/2, getHeight()/2);
        
        
    }
    
    protected void setConfirmType(char type) {
        this.type = type;        
    }

    protected void addListener(ActionListener action) {
        button1.addActionListener(action);
        button2.addActionListener(action);
    }

    private void drawComponent(int width, int height) {
        
        switch(type){
            case START:
                drawStart(width, height);
                break;
            case CONTINUE:
                drawContinue(width, height);
                break;
        }
        
       
    }

    private void drawStart(int width, int height) {
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
        message.setLocation(width-200, height-60);
        button1.setText("START");
        button1.setLocation(width-50, height+50);
        button1.setActionCommand(GameFrame.START);
        button2.setVisible(false);
    }

    private void drawContinue(int width, int height) {
        message.setText("Dou you want continue or start a new game?");
        button1.setText("new game");        
        button2.setText("continue");
        button2.setVisible(true);
        message.setLocation(width-150, height-50);        
        button1.setLocation(width-180, height+70);
        button2.setLocation(width+90, height+70);
        
        button1.setActionCommand(GameFrame.NEW_GAME);
        button2.setActionCommand(GameFrame.CONTINUE);
        
    }

    
    
}
