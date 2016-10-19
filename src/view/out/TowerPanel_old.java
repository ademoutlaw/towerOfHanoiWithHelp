/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author outlaw
 */
public class TowerPanel_old extends JPanel{
    
    private static final int STACK_WIDTH =200;
    public static final int MOVE=0;
    public static final int DISELECT=1;
    public static final int SELECT=2;
    private static final int LEFT=1;
    private static final int RIGHT=2;
    private static final int UP=3;
    private static final int DOWN=4;
    
    private ArrayList<Integer> stack;
    private int disc;
    private boolean selected;
    private int posY;
    private final char name;
        
    private static int movedDisckX;
    private static int movedDisckY;
    private static boolean toMove;
    private static char currentStack;
    private static int movedDisck;
    private static char toStack;
    private static int direction;
    private static int horizontal;
    private static int finishY;
    private static int finishX;
    private static Image discImage;
             
    
    protected TowerPanel_old(char name) {
        //isSet=true;
        this.name = name;
        stack = new ArrayList();
        setOpaque(false);
        setPreferredSize(new Dimension(220,350));
        if(discImage == null){
            URL url = getClass().getResource("/view/images/disc.png");
            discImage = new ImageIcon(url).getImage();
        }
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        paintStack(g);
        paintDiscks(g);        
    }

    private void paintStack(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(110, 40, 5, 300);
        g.fillRect(10, 310, 200, 30);
        g.setColor(Color.yellow);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        g.drawString(""+name, 107, 330);
               
    }
    
    private void paintDisc(Graphics g,int disc, int discX, int discY , boolean selected){
        int py = discY;
        if(selected){
            py=discY-4;
            //g.setColor(Color.blue); 
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke((float) 1.5));
            g2.drawRoundRect(discX, py, disc*10, 18,10,10);
        }
        //g.setColor(Color.blue.darker()); 
        g.fillRoundRect(discX, py, disc*10, 18,10,10);
        //g.drawImage(discImage, discX, py, disc*10, 18, Color.yellow, this);
    }
    
    private void paintDiscks(Graphics g){
        posY =290;
        int last=0;
        
        if(toMove && name==toStack){
            last=1;
        }
        for(int i=0;i<stack.size()-last;i++){
            int disc=stack.get(i);
            paintDisc(g,disc,posX(disc),posY,false);
            posY -= 19;
            // TODO : g.drawImage(null, disk, disk, disk, disk, i, i, i, i, this);
        } 
        paintSelectedDisck(g);
        paintMovedDisck(g);
    }
    
    private void paintSelectedDisck(Graphics g){
        if(selected){
            paintDisc(g,disc,posX(disc),posY,true);          
        }
    }
    
    private void paintMovedDisck(Graphics g){
        if(toMove && name == currentStack){
            updateMovedDisck();
            paintDisc(g,movedDisck,movedDisckX,movedDisckY,false);
        }
    }
    
    protected void addAMouseListener(MouseListener l){
        addMouseListener(l);
    }
    
    public char getPanelName() {
        return name;
    }
    
    private void updateMovedDisck(){
        //System.out.println("eeee");
        if(toMove){
            switch(direction){
                case UP:
                    if((movedDisckY--)<=0){
                        direction=horizontal;
                    }
                    break; 
                case DOWN:
                    if((movedDisckY++)>=finishY){
                       toMove=false;
                       
                    }
                    break;
                case LEFT:
                    if(currentStack != toStack){
                        if((movedDisckX--)<=0){
                            movedDisckX=STACK_WIDTH;
                            currentStack--;
                        }
                    }else{
                        if((movedDisckX--)<=finishX){
                           direction=DOWN;
                        }
                    }
                    break;
                case RIGHT:
                    if(currentStack != toStack){
                        if((movedDisckX++)>=STACK_WIDTH){
                            movedDisckX=0;
                            currentStack++;
                        }
                    }else{
                        if((movedDisckX++)>=finishX){
                           direction=DOWN;
                        }
                    }
                    break;       
            }
        }
    }
    
    protected void loadStackPanel(int size) {
        // TODO : animation
        for(int i=size;i>0;i--){
            stack.add(i);
        }
    }
 
    protected void updateStackPanel(ArrayList<Integer> discksSizes, int status, char from, char to, int disck) {        
        switch(status){
            case TowerPanel_old.MOVE:
                drawMove(from,to,disck);
                break;
            case TowerPanel_old.SELECT:
                drawSelected(from,disck);
                break;
            default:
                this.selected=false;
                break;
        }
        stack=discksSizes;        
    }

    private void drawMove(char from, char to, int disck) {
        selected=false; 
        if(from!=to){
           
            if(name==from){
                movedDisck=disck;
                toMove=true;
                direction=UP;
                movedDisckX=posX(disck);
                movedDisckY=posY;
                currentStack=from;
                horizontal = from>to?LEFT:RIGHT;
                toStack=to;
            }else if(name == to){                
                finishX = posX(disck);
                finishY = posY;
            }     
        }
    }
        
    private void drawSelected(char from, int disck) {
        this.disc=disck;
        this.selected=this.name==from;
    }

    private int posX(int disck) {   
        return 112-disck*5;
    }
    
    public static Image scaleImage(Image source, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return img;
    }
}
