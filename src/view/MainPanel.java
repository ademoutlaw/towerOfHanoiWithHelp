/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class MainPanel extends JLayeredPane{
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;

    public MainPanel() {
        panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel2 = new JPanel();
        panel2.setBackground(Color.green);      
        panel3 = new JPanel();
        panel3.setBackground(Color.blue); 
        this.setBackground(Color.red);
        this.add(panel1, JLayeredPane.DEFAULT_LAYER);
        this.add(panel2, JLayeredPane.DEFAULT_LAYER);
        this.add(panel3, JLayeredPane.DEFAULT_LAYER);
        this.setLayer(panel1, 1);
        this.setLayer(panel2, 1);
        this.setLayer(panel3, 1);
        this.setPreferredSize(new Dimension(100,100));
        
    }
    
}
