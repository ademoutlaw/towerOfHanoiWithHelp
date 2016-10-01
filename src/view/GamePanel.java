/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author outlaw
 */
public class GamePanel extends JPanel{
    private final URL urlBackground;

    public GamePanel() {
        urlBackground = getClass().getResource("/view/images/background.gif");
        Timer timer = new Timer(5,new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
               // int x = (this.getWidth()-imgBackground.getWidth())/2;
                //int y = (this.getHeight()-imgBackground.getHeight());
                URL test = null;
                Image image = new ImageIcon(urlBackground).getImage();
                //System.out.println("test");
                g.drawImage(image, 0, 0, null);
    }
    
    
}
