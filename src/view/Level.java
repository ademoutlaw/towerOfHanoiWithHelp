/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author outlaw
 */
public class Level extends JLabel{
    String level;
    boolean locked;
    private static ImageIcon icon;
    
    public Level(String level, boolean locked) {
        this.level = level;
        this.locked = locked;
        
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(100,100));
        this.setMaximumSize(new Dimension(100,100));
        if(icon==null){
            URL url = getClass().getResource("/view/images/background.jpg");
            icon = new ImageIcon(url);
        }
        
        //this.setIgnoreRepaint(true);
        this.setBackground(Color.red);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        
        g.drawImage(icon.getImage(), 0, 0,100,100, null);
        g.setColor(new Color(0,251,97,115));
        g.fill3DRect(0, 0, 100, 100, true);
        g.setColor(Color.white);
        g.drawString(level, 50, 50);
    }
    
    public void setLevelLocatin(int x, int y){
        this.setLocation(x, y);
        
    }
    
}
