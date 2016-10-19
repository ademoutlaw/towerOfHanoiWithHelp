/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author outlaw
 */
public class Test extends JPanel implements ActionListener, MouseListener{
    private int value1;
    private final int min1;
    private final int min2;
    private final int max1;
    private final int max2;
    private final Timer timer;
    private final Section panel;
    private final ButtonIcon icon1;
    private final ButtonIcon icon2;
    private final ButtonIcon icon3;
    //private final Image image20;
    private boolean done;
    private final LabelText text;
    private JButton btn;
    

    public Test() {
        timer = new Timer(10,this);
        timer.start();
        value1 = max2 = min1 = 60;
        max1 = 420;
        min2 = 210;      
        String extension = ".png";
        String path = "/view/images/";;
        String iconName = "undo";
        
        Image image10 = getImage(path+iconName+extension);
        Image image11 = getImage(path+iconName+"_rollover"+extension);
        Image image12 = getImage(path+iconName+"_pressed"+extension);
        Image image13 = getImage(path+iconName+"_disabled"+extension);
        icon1 = new ButtonIcon(image10,image11,image12,image13,0,0,64,64);
        icon1.setCmd(iconName);
        iconName = "redo";
        image10 = getImage(path+iconName+extension);
        image11 = getImage(path+iconName+"_rollover"+extension);
        image12 = getImage(path+iconName+"_pressed"+extension);
        image13 = getImage(path+iconName+"_disabled"+extension);
        icon2 = new ButtonIcon(image10,image11,image12,image13,64,0,64,64);
        icon2.setCmd(iconName);
        iconName = "next";
         image10 = getImage(path+iconName+extension);
         image11 = getImage(path+iconName+"_rollover"+extension);
         image12 = getImage(path+iconName+"_pressed"+extension);
         image13 = getImage(path+iconName+"_disabled"+extension);
        icon3 = new ButtonIcon(image10,image11,image12,image13,128,0,64,64);
        icon3.setCmd(iconName);
        text = new LabelText("testing", 15, 80);
        panel = new Section();
        panel.add(icon1);
        panel.add(icon2);
        panel.add(text);
        panel.add(icon3);
       // System.out.println(image20);
        btn = new JButton();
        btn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e);
            }
            
        });
        addMouseListener(this);
       
    }
    private Image getImage(String img){
        URL url  = getClass().getResource(img);
        if (url==null)
            return null;
        
        return new ImageIcon(url).getImage(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        Test pano = new Test();
        pano.setBackground(Color.blue);
        frame.add(pano);
        frame.setUndecorated(true);
        frame.setLocation(200, 70);
        frame.setMinimumSize(new Dimension(500,400));
        frame.setBackground(Color.yellow);
        frame.setVisible(true);
        
        System.out.println("hola");
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        drawBig(g);
        drawSmal(g);
        value1++;
        if(value1>max1){
            value1 = 0;
            icon1.setX(0);
            done = !done;
            if(done){
            //panel.disabled();
                panel.ready();
            }else{
                panel.pressed();
                //panel.ready();
            }
            //timer.stop();
        }
        panel.paint(g);
        
        icon1.setX(icon1.getX()+1);
        
        //g.drawImage(image20, 6, 5, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
    }

    private void drawBig(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(min1, 20, 10, 10);
        g.drawLine(min1, 25, value1, 25);
        g.fillOval(value1, 20, 10, 10);
    }

    private void drawSmal(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(min2, 200, 10, 10); 
        
        int value2 = (int)get(value1,min1,max1,min2,max2);
        //System.out.println(value1+"==>"+value2+" ? ");
         g.drawLine(min2, 205, value2, 205);
        g.fillOval(value2, 200, 10, 10);
    }

    private float get(float value1, float min1, float max1, float min2, float max2) {
        return min2 + (max2 - min2) / (max1-min1) * (value1-min1);       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.clicked(e.getX(), e.getY(), btn);
    }

    @Override
    public void mousePressed(MouseEvent e) {    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}
