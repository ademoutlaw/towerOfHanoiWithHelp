<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
public class StackPanel extends JPanel{
    
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
    private int posX;
    private final char name;
    private Timer timer;
    
    private int stepX;
    private int stepY;
    private boolean moved;
    
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
    
    
    
    
    
    
    
    protected StackPanel(char name) {
        this(Color.orange, name);
        
    }

    protected StackPanel(Color red, char name) {
        //isSet=true;
        this.name = name;
        stack = new ArrayList();
        setBackground(red);
        setPreferredSize(new Dimension(220,350));
        timer = new Timer(1, new ActionListener(){
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
        g.fillRect(110, 10, 5, 300);
        g.fillRect(10, 310, 200, 30);
        g.setColor(Color.white);
        g.drawString(""+name, 100, 320);
               
    }
    
    private void paintDiscks(Graphics g){
        posY =290;
        int last=0;
        g.setColor(Color.blue); 
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
            g.setColor(Color.yellow);            
            g.drawRoundRect(posX(disck), posY-4, disck*10, 18,10,10);
        }
    }
    
    private void paintMovedDisck(Graphics g){
        //System.out.println(toMove);
        if(toMove && name == currentStack){
            g.setColor(Color.pink);
            //System.out.println("stack:"+name+" ==> x= "+movedDisckX+ " y= "+movedDisckY+" disk= "+movedDisck);
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
            case StackPanel.MOVE:
                drawMove(from,to,disck);
                break;
            case StackPanel.SELECT:
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
        
        System.out.println("satck : "+name+" => from : " +from+ " to : "+to+" disck : "+disck);
        if(from!=to){
            timer.start();
            if(name==from){
               // System.out.println(disck);
                movedDisck=disck;
                toMove=true;
                //timer.start();
                direction=UP;
                movedDisckX=posX;
                movedDisckY=posY;
                currentStack=from;
                horizontal = from>to?LEFT:RIGHT;
                toStack=to;
                System.out.println("stack:"+name+" ==> x= "+movedDisckX+ " y= "+movedDisckY+" disk= "+movedDisck);
            }else if(name == to){
                System.out.println("stack:"+name+" ==> x= "+movedDisckX+ " y= "+movedDisckY+" disk= "+movedDisck);
                
                finishX = posX(movedDisck);
                finishY = posY;
            }     
        }
    }
        
    private void drawSelected(char from, int disck) {
        this.disck=disck;
        this.selected=this.name==from;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int posX(int disck) {   
        posX= 112-disck*5;
        return posX;
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class StackPanel extends JPanel{
    private ArrayList stack;
    private final int Height=300;
    private int base;
    private int posX;
    
    public StackPanel() {
        this(Color.orange);
        
    }

    public StackPanel(Color red) {
        stack = new ArrayList();
        stack.add(6);
        stack.add(5);
        stack.add(4);
        stack.add(3);
        stack.add(2);
        stack.add(1);
        
        base=HEIGHT;
        
        //for test
        posX=0;
        setBackground(red);
        setPreferredSize(new Dimension(220,350));
        this.addAc
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        paintStack(g);
        
    }

    private void paintStack(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(110, 10, 5, 300);
        g.fillRect(10, 310, 200, 30);
        g.setColor(Color.blue);
        //g.fillRect(posX, base, 100, 50);
        System.out.println("========= "+base);
        base=290;
        posX=22;
        for(Object disk:stack){
            System.out.println(disk);
            g.fillRoundRect(posX, base, (int)disk*30, 20,10,10);
            
            posX += 15;
            base -= 21;
        }
    }
    
    
    
}
>>>>>>> 345a305dc6e6f9fa62c849f2c87d5d0d823dbfcf
