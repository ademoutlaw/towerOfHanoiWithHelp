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
    
    private static final int POLE_A = 100;
    private static final int POLE_B = 300;
    private static final int POLE_C = 500;
    
    private static final int DISC_HEIGHT = 16;
    private static final int PANEL_HEIGHT = 350;
    
    public static final char MOVE = 'm';
    public static final char SELECT = 's';
    public static final char DESELECT = 'd';
    
    public static final char TOWER_A = 'a';
    public static final char TOWER_B = 'b';
    public static final char TOWER_C = 'c';
        
    private List<Integer> towerA;
    private List<Integer> towerB;
    private List<Integer> towerC;
    
    private boolean isSelected;
    private boolean isMoving;
    
    private int poleTo;
    private int poleFrom;
    private int direction;
    private int currentDisc;
    private int currentDiscX;
    private int currentDiscY;
    private int pole;
    private int [] frameRate;
    private double walked;
    private int hoveredPole;
    private final Color colorHover;
    private final Color colorClick;
    private boolean error;
    private Color colorError;
    private boolean click;
    private int fps;
    

    public TowerPanel() {  
        this.frameRate = new int[]{0, 0, 0};
        
        setPreferredSize(new Dimension(600,350));
        
        colorHover = new Color(255, 255, 0, 30);
        colorClick = new Color(255, 255, 0, 100);
        colorError = new Color(255, 0, 0, 220);
        
        setOpaque(false);  
        /*towerA = new ArrayList<>();
        towerB = new ArrayList<>();
        towerC = new ArrayList<>(); */       
        isSelected=true; 
        addMouseMotionListener(this);
    }
        
    @Override
    protected void paintComponent(Graphics g) {        
        //super.paintComponent(g); 
        paintTowers(g);
        paintDiscs(g);
    }
    
    private void paintTowers(Graphics g){
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
            int x[]={hoveredPole+4,hoveredPole-100,hoveredPole+100};
            int y[]={35,350,350};
            g.fillPolygon(x, y, 3);
        }
        
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
        g.fillRect(0, 320, 600, 30);
    }
    
    private void paintDiscs(Graphics g){          
        paintDiscs(g,towerA,POLE_A);
        paintDiscs(g,towerB,POLE_B);
        paintDiscs(g,towerC,POLE_C);       
        paintSelectedDisc(g);
        paintWorkedDisc(g);
    }
    
    private void paintDiscs(Graphics g,List<Integer> list, int center){
        int posY=PANEL_HEIGHT-DISC_HEIGHT-30;
        g.setColor(Color.green);        
        int minus = 0;
        if(isMoving &&center == poleTo){
            minus = -1;            
        }
        if(list!=null)
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
    
    protected void loadTower() {   
        hideTower(towerA);
        hideTower(towerB);
        hideTower(towerC);       
    }
    
    private void setHover(MouseEvent e){
        pole = getPole(getTowerName(e));
    }

    protected void setTowers(List listA, List listB, List listC) {        
        towerA=listA;
        towerB=listB;
        towerC=listC;
    }
    
    protected List<Integer> getTowerByName(char tower){
        switch(tower){
            case TOWER_A:
                return towerA;
            case TOWER_B:
                return towerB;
            case TOWER_C:
                return towerC;            
        }
        return null;        
    }
    
    protected void setAction(char actionName,char from , char to ,int disc){        
        switch(actionName){
            case GameFrame.MOVE_FAST:
                isMoving = true;
                isSelected = false;
                fps = 30;
                break;
            case GameFrame.MOVE:
                click = true;
                isMoving = true;
                isSelected = false;
                fps = 80;
                break;
            case GameFrame.SELECT:
                click = true;
                isSelected = true;
                isMoving = false;
                break;
            case GameFrame.DESELECT_ERR:
                error=true;                
            case GameFrame.DESELECT:
                click = true;
                isSelected = false;
                isMoving = false;
                break;
            default:
                return;   
        }
        poleFrom = getPole(from);
        poleTo = getPole(to);
        direction = poleFrom<poleTo?1:-1;
        currentDisc=disc;
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
   
    private void paintWorkedDisc(Graphics g) {
        if(isMoving){
            int distance = getDistance();            
            walked++;
            int walker = (int) map (walked,0,fps,0,distance);
            walking(walker);
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
            int x = tower.get(i);
            tower.set(i, -x);
        }        
    }

    private void dropDisc(Graphics g, int center, int posY,int disc, List<Integer> list, int i) {
        int j = center / (POLE_A *2);
        frameRate[j]++;
        int pos =(int) map(frameRate[j],0,10,0,posY);
        g.fillRoundRect(getPosX(-disc,center), pos, -disc*18, DISC_HEIGHT, 18, 18);
        if(frameRate[j]>=10){
           list.set(i, -disc);
           frameRate[j] = 0;
        }
    }

    private double map(double value1, double min1, double max1, double min2, double max2) {
        return min2 + (max2 - min2) / (max1-min1) * (value1-min1);       
    }

    private int getDistance() {
        return getPosY(poleTo)+getPosY(poleFrom)+Math.abs(poleTo-poleFrom);   
    }

    private void walking(int walker) {
        int up = getPosY(poleFrom);
        if(walker >up){
            int horizontal = Math.abs(poleTo-poleFrom);
            if(walker >up + horizontal){
                int down = getPosY(poleTo);
                if(walker > up + horizontal + down){
                    isMoving=false;
                    walked = 0;                    
                }else{
                    currentDiscX = getPosX(currentDisc,poleTo);
                    currentDiscY = walker - up - horizontal; 
                }
            }else{                
                currentDiscX = getPosX(currentDisc,poleFrom+(walker-up)*direction);                
                currentDiscY = 0;                
            }
        }else{
            currentDiscX = getPosX(currentDisc,poleFrom);
            currentDiscY = up - walker;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hover(e.getX());
    }

    private char hover(int x) {
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
                    return ' ';
                }
            }
        }
    }
           
}
