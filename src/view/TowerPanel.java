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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author outlaw
 */
public class TowerPanel extends JPanel{
    
    private static final int STACK_WIDTH =200;
    public static final int MOVE=0;
    public static final int DISELECT=1;
    public static final int SELECT=2;
    private static final int LEFT=1;
    private static final int RIGHT=2;
    private static final int UP=3;
    private static final int DOWN=4;
    
    private ArrayList<Integer> stack;
    private int disck;
    private boolean selected;
    private int posY;
    private final char name;
    private Timer timer;
        
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
             
    
    protected TowerPanel(char name) {
        //isSet=true;
        this.name = name;
        stack = new ArrayList();
        setOpaque(false);
        setPreferredSize(new Dimension(220,350));
        timer = new Timer(20, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMovedDisck();
            }          
        });
        //timer.setInitialDelay(pause);
        //timer.start(); 
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        paintStack(g);
        paintDiscks(g);
        paintSelectedDisck(g);
        paintMovedDisck(g);
    }

    private void paintStack(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(110, 40, 5, 300);
        g.fillRect(10, 310, 200, 30);
        g.setColor(Color.yellow);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        g.drawString(""+name, 107, 330);
               
    }
    
    private void paintDiscks(Graphics g){
        posY =290;
        int last=0;
        g.setColor(Color.blue.darker()); 
        if(toMove && name==toStack){
            last=1;
        }
        for(int i=0;i<stack.size()-last;i++){
            int disk=stack.get(i);
            g.fillRoundRect(posX(disk), posY, disk*10, 18,10,10);
            posY -= 19;
        }
            
        //}
        
    }
    
    private void paintSelectedDisck(Graphics g){
        if(selected){
            
            g.fillRoundRect(posX(disck), posY-4, disck*10, 18,10,10);
            g.setColor(Color.blue); 
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke((float) 1.5));
            g2.drawRoundRect(posX(disck), posY-4, disck*10, 18,10,10);
        }
    }
    
    private void paintMovedDisck(Graphics g){
        if(toMove && name == currentStack){
            g.setColor(Color.blue.darker());
            g.fillRoundRect(movedDisckX, movedDisckY, movedDisck*10,18,10,10);
        }
    }
    
    protected void addAMouseListener(MouseListener l){
        addMouseListener(l);
    }
    
    public char getPanelName() {
        return name;
    }
    
    private void updateMovedDisck(){
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
                       timer.stop();
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
        repaint();
        
    }
    
    protected void loadStackPanel(int size) {
        for(int i=size;i>0;i--){
            stack.add(i);
        }
        repaint();
    }
 
    protected void updateStackPanel(ArrayList<Integer> discksSizes, int status, char from, char to, int disck) {        
        switch(status){
            case TowerPanel.MOVE:
                drawMove(from,to,disck);
                break;
            case TowerPanel.SELECT:
                drawSelected(from,disck);
                break;
            default:
                this.selected=false;
                break;
        }
        stack=discksSizes;
        repaint();        
    }

    private void drawMove(char from, char to, int disck) {
        selected=false; 
        if(from!=to){
            timer.start();
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
        this.disck=disck;
        this.selected=this.name==from;
    }

    private int posX(int disck) {   
        return 112-disck*5;
    }
    
}
