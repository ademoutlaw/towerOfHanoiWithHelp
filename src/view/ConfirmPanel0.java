/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
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
public  class ConfirmPanel0 extends JDialog{
    
    public static char START = 's';
    public static char CONTINUE = 'c';
    
    private final JButton button1;
    private final JButton button2;
    private final JLabel message;
    private final MainPanel panel;

    public ConfirmPanel0() {
        //super(frame, false);
        button1 =  new JButton();
        button2 = new JButton(); 
        message = new JLabel();
        panel = new MainPanel();
        setUndecorated(true);
        //setLocation(50, 50);
        panel.setBackground(new Color(0,0,0,100));
        setOpacity(0.5f);
        add(panel);
        //setVisible(true);
    }

    protected void setConfirm(char c) {
        
    }

    protected void addListener(ActionListener action) {
    }

    private class MainPanel extends JPanel {

        public MainPanel() {
           add(message);
           add(button1);
           add(button2);
        }
    }
    
}
