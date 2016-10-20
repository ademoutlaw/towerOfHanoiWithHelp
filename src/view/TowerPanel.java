/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class TowerPanel extends JPanel implements MouseMotionListener {
    
    private static final int POLE_A = 110;
    private static final int POLE_B = 330;
    private static final int POLE_C = 550;
    private static final int DISC_HEIGHT = 16;
    private static final int PANEL_HEIGHT = 350;
    private static final int PANEL_WIDTH = 670;
    public static final char MOVE = 'm';
    public static final char SELECT = 's';
    public static final char DESELECT = 'd';
    public static final char TOWER_A = 'a';
    public static final char TOWER_B = 'b';
    public static final char TOWER_C = 'c';   
    public static final char GAP = '_';   
    private List<Integer> towerA;
    private List<Integer> towerB;
    private List<Integer> towerC;
    private boolean isSelected;
    private boolean isMoving;
    private boolean error;
    private boolean click;
    private int poleTo;
    private int poleFrom;
    private int direction;
    private int currentDisc;
    private int currentDiscX;
    private int currentDiscY;
    private int hoveredPole;
    private int fps;
    private int pole;
    private double walked;
    private final int [] frameDrawed;
    private final Color colorHover;
    private final Color colorClick;
    private final Color colorError;
    private boolean ready;
    private int up;
    private int down;
    private int horizontal;
    private int speed;

    public TowerPanel() {  
        speed =80;
        frameDrawed = new int[]{0, 0, 0, 0};        
        setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));        
        colorHover = new Color(255, 255, 0, 30);
        colorClick = new Color(255, 255, 0, 100);
        colorError = new Color(255, 0, 0, 220);        
        setOpaque(false);        
        isSelected=true; 
        addMouseMotionListener(this);        
    }
        
    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g); 
        paintTowers(g);
        paintDiscs(g);
    }
    
    private void paintTowers(Graphics g){
        paintRolloverTower(g);
        
        g.setColor(Color.BLACK); 
        g.fillRect(POLE_A, 40, 10, 350);
        g.fillRect(POLE_B, 40, 10, 350);
        g.fillRect(POLE_C, 40, 10, 350);         
        if(pole>0){
            g.setColor(Color.red);
            g.fillRect(pole, 40, 10, 350);
            g.setColor(Color.BLACK); 
            pole=0;
        }        
        g.fillRect(0, 320, PANEL_WIDTH, 30);
    }

    private void paintRolloverTower(Graphics g) {
        if(hoveredPole>0){
            if(error||click){
                if(error){
                    g.setColor(colorError);
                    error = false;
                }else{
                    g.setColor(colorClick);
                    click = false;
                }                
            }else{
                g.setColor(colorHover);
            }
            int x[]={hoveredPole+4,hoveredPole-110,hoveredPole+110};
            int y[]={35,350,350};
            g.fillPolygon(x, y, 3);
        }
    }
    
    private void paintDiscs(Graphics g){          
        paintDiscs(g,towerA,POLE_A);
        paintDiscs(g,towerB,POLE_B);
        paintDiscs(g,towerC,POLE_C);       
        paintSelectedDisc(g);
        paintWalkedDisc(g);
    }
    
    private void paintDiscs(Graphics g,List<Integer> list, int center){
        int posY=PANEL_HEIGHT-DISC_HEIGHT-30;
        g.setColor(Color.green);        
        int minus = 0;
        if(isMoving &&center == poleTo){
            minus = -1;            
        }
        if(list!=null){
            for(int i=0;i<list.size()+minus;i++){
                int disc = list.get(i);
                
                if(disc<0){                    
                    dropDisc(g, center,posY,disc,list,i);                
                    break;
                }
                
                g.fillRoundRect(getPosX(disc,center), posY, disc*18, DISC_HEIGHT, 18, 18);
                posY-=DISC_HEIGHT+1;
            }  
        }
    }
    
    protected void loadTower() {   
        hideTower(towerA);
        hideTower(towerB);
        hideTower(towerC);       
    }
    
    protected void setTowers(List listA, List listB, List listC) {    
        isMoving = false;
        isSelected = false;
        currentDisc = 0;
        towerA=listA;
        towerB=listB;
        towerC=listC;
    }
        
    protected void setAction(char actionName, char from , char to , int disc){        
        switch(actionName){
            case GameFrame.MOVE_FAST:
                moveFast();
                break;
            case GameFrame.MOVE:
                move();
                break;
            case GameFrame.SELECT:
                select();
                break;
            case GameFrame.DESELECT_ERR:
                error=true;                
            case GameFrame.DESELECT:
                deselect();
                break;
            default:
                return;   
        }
        poleFrom = getPole(from);
        poleTo = getPole(to);
        currentDisc=disc;
        setMove();
    }

    private void deselect() {
        click = true;
        isSelected = false;
        isMoving = false;
    }

    private void select() {
        click = true;
        isSelected = true;
        isMoving = false;
    }

    private void move() {
        click = true;
        isMoving = true;
        isSelected = false;
        ready = false;
        walked = 0;
        fps = speed;
        
    }

    private void setMove() {
        if(isMoving){
            up = getPosY(poleFrom);
            down = getPosY(poleTo);
            direction = poleFrom<poleTo?1:-1;
            horizontal = direction*(poleTo-poleFrom);            
        }
    }

    private void moveFast() {
        move();
        fps = 30;
    }
    
    private int getPole(char tower){        
        switch(tower){
            case TOWER_A:
                return POLE_A;               
            case TOWER_B:
                return POLE_B;
            case TOWER_C:
                return POLE_C;
            default:
                return 0;            
        }        
    }
    
    private int getPosX(int disc, int center) {        
        return center-disc*18/2+4;        
    }

    private void paintSelectedDisc(Graphics g) {
        if(isSelected) {            
            g.setColor(Color.green);            
            g.fillRoundRect(getPosX(currentDisc, poleFrom), getPosY(poleFrom)-6, currentDisc * 18,
                   DISC_HEIGHT, 18, 18);
        }        
    }
   
    private void paintWalkedDisc(Graphics g) {
        if(isMoving){
            walking();
            g.setColor(Color.green);
            g.fillRoundRect(currentDiscX,currentDiscY , currentDisc * 18,
                   DISC_HEIGHT, 18, 18);
        }
    }
        
    protected char getTowerName(MouseEvent e) {        
        return hover(e.getX());
    }

    private int getPosY(int poleFrom) {
        switch(poleFrom){
            case POLE_A:
                return PANEL_HEIGHT-DISC_HEIGHT-30-towerA.size()*(DISC_HEIGHT+1);
            case POLE_B:
                return PANEL_HEIGHT-DISC_HEIGHT-30-towerB.size()*(DISC_HEIGHT+1);
            case POLE_C:
                return PANEL_HEIGHT-DISC_HEIGHT-30-towerC.size()*(DISC_HEIGHT+1);
        }
        return 0;
    }

    private void hideTower(List<Integer> tower) {
        for(int i = 0;i<tower.size();i++){
            tower.set(i, -tower.get(i));
            frameDrawed[3]++;
        }        
    }

    private void dropDisc(Graphics g, int center, int posY,int disc, List<Integer> list, int i) {
        int j = center / (POLE_A *2);
        frameDrawed[j]++;
        ready =false;
        int pos =(int) map(frameDrawed[j],0,10,0,posY);
        g.fillRoundRect(getPosX(-disc,center), pos, -disc*18, DISC_HEIGHT, 18, 18);
        if(frameDrawed[j]>=10){
           list.set(i, -disc);
           frameDrawed[j] = 0;
           frameDrawed[3]--;
           if(frameDrawed[3]<=0){
               ready = true;
           }
          
        }
    }

    private double map(double value1, double min1, double max1, double min2, double max2) {
        return min2 + (max2 - min2) / (max1-min1) * (value1-min1);       
    }
    
    private void walking() {                
        walked++;
        int walk = (int) map (walked,0,fps,0,up+down+horizontal);        
        if(walk >up){            
            if(walk >up + horizontal){                
                if(walk > up + horizontal + down){
                    isMoving=false;
                    walked = 0;  
                    ready = true;
                }else{
                    currentDiscX = getPosX(currentDisc,poleTo);
                    currentDiscY = walk - up - horizontal; 
                }
            }else{                
                currentDiscX = getPosX(currentDisc,poleFrom+(walk-up)*direction);                
                currentDiscY = 0;                
            }
        }else{
            currentDiscX = getPosX(currentDisc,poleFrom);
            currentDiscY = up - walk;
        }
    }
    
    void setSpeed(int speed) {
        this.speed = speed;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        hover(e.getX());
    }

    private char hover(int x) {
        if(!ready){
            return GAP;
        }
        if(x>POLE_A-70 && x <POLE_A+70){
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            hoveredPole = POLE_A;
            return TOWER_A;  
        }else{
            if(x>POLE_B-70 && x <POLE_B+70){
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                hoveredPole = POLE_B;
                return TOWER_B;  
            }else{
                if(x>POLE_C-70 && x <POLE_C+70){
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    hoveredPole = POLE_C;
                    return TOWER_C;  
                }else{
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    hoveredPole = 0;
                    return GAP;
                }
            }
        }
    }
    
     @Override
    public void mouseDragged(MouseEvent e) {
    }

    
           
}
