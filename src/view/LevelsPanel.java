/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author outlaw
 */
public class LevelsPanel extends JPanel implements MouseMotionListener {
    private final static int FPS = 10;
    private final Image backgroundImg;
    private final int cote = 100;
    private final Image lockedImg;
    private final Font fontText;
    private int level ;
    private int selectedLevel;
    private int errorLevel;
    private int acceptedLevel;
    private final Color open;
    private final Color textColor;
    private final Color locked;
    private int frameRate;
    private int fps;


    public LevelsPanel() {
        URL url = getClass().getResource("/view/images/background.jpg");
        ImageIcon icon = new ImageIcon(url);        ;     
        backgroundImg = icon.getImage();
        url = getClass().getResource("/view/images/locked.png");
        icon = new ImageIcon(url);
        lockedImg = icon.getImage();
        fontText = new Font(Font.SANS_SERIF,Font.PLAIN,80); 
        textColor = Color.YELLOW.brighter().brighter().brighter().brighter();
        open = new Color(0,251,97,115); 
        locked = new Color(122, 187, 148,150);
        fps = FPS;
        addMouseMotionListener(this);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(),null);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(textColor);        
        paintLevels(g);
    }

    private void paintLevels(Graphics g) {        
        int marginLeft = (getWidth()-cote*4)/5;
        int marginTop = (getHeight()-cote*3)/4;        
        int pX ;
        int pY = 0;
        int apX = 0;
        Graphics2D g2 = (Graphics2D)g;
        int apY = 0;
        for(int i=0;i<3;i++){            
            pY+=marginTop;
            pX = 0;
           for(int j=1;j<=4;j++){
               pX+=marginLeft;               
               int x = i*4+j;                       
               if(x<=level){
                    paintOpenLevels(g, g2, open, pX, pY, cote, cote, x); 
               }
               else{
                    paintLockedLevels(g, pX, pY , x == errorLevel); 
               }    
               if(acceptedLevel == x){
                   apX = pX;
                   apY = pY;
               }
               pX+=cote;                                          
            } 
           pY+=cote;
        }    
        if(acceptedLevel>0){            
            paintAcceptedLevel(g, g2, apX, apY, acceptedLevel);          
        }
    }

    private void paintLockedLevels(Graphics g, int pX, int pY, boolean err) {        
        g.setColor(err?Color.RED:locked);
        g.fill3DRect(pX, pY, cote, cote, true);
        g.drawImage(lockedImg, pX, pY,cote,cote, null);
        if(err)
            errorLevel=0;
    }

    private void paintOpenLevels(Graphics g, Graphics2D g2,Color open, int pX, int pY, int coteW, int coteH, int level) {
        if(level ==  selectedLevel) {
            pX--;
            pY--;
            coteW+=2;
            coteH+=2;
        }
        g2.setStroke(new BasicStroke(2));        
        g.drawImage(backgroundImg, pX, pY,coteW,coteH, null);
        g.setColor(open);
        g2.fillRect(pX, pY, coteW, coteH);
        g.setColor(textColor);
        g.setFont(fontText);
        g2.drawRect(pX, pY, coteW+1, coteH+1);
        int d= level>9?-15:0;
        g.drawString(Integer.toString(level), pX+20+d, pY+70);                 
    }
    
    private void paintAcceptedLevel(Graphics g, Graphics2D g2, int pX, int pY, int level){
        
        double left = map(frameRate,0,fps,pX,0);
        double up = map(left,0,pX,0,pY);
        double right = map(left,0,pX,getWidth(),cote);
        double down = map(left,0,pX,getHeight(),cote);
        double alpha = map(left, 0, pX,0,115);
        
        paintOpenLevels(g, g2,new Color(0,251,97,(int)alpha), (int)left,(int) up,(int) right,(int) down, level); 
        if(frameRate>=fps){
            acceptedLevel = 0;
            frameRate = 1;
        }else{
            frameRate++;
        }        
    }
    
    private int getLevel(int x, int y){
        
        int zoneX = x/(getWidth()/4);
        int zoneY = y/(getHeight()/3);
        int marginLeft = (getWidth()-cote*4)/5;
        int marginTop = (getHeight()-cote*3)/4;
        int l = zoneY*4+zoneX+1;    
        
        if(x<(zoneX+1)*marginLeft+zoneX*cote||x>(zoneX+1)*marginLeft+(zoneX+1)*cote
                ||y<(zoneY+1)*marginTop+zoneY*cote||y>(zoneY+1)*marginTop+(zoneY+1)*cote){
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            selectedLevel = 0;
            return 0;
        }
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));  
        selectedLevel = l;
        return l;
    }
    
    protected int getLevel(MouseEvent e){                                
        return getLevel(e.getX(),e.getY());        
    }

    protected void setLevel(int level) {
        this.level = level;
        frameRate = 0;
        acceptedLevel = 0;
    }
    
    protected void errorLevel(int l) {
        errorLevel=l;
    }

    protected void acceptLevel(int l) {
        acceptedLevel = l;
    }
    
    private double map(double value1, double min1, double max1, double min2, double max2) {
        return min2 + (max2 - min2) / (max1-min1) * (value1-min1);       
    }

    protected void setFps(int fps) {
        this.fps = fps;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        getLevel(e);
    }

    
    
}
